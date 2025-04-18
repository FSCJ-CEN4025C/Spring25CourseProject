package edu.fscj.cen3024c.financialclarity.service;

import edu.fscj.cen3024c.financialclarity.dto.ExpensesDTO;
import edu.fscj.cen3024c.financialclarity.entity.Category;
import edu.fscj.cen3024c.financialclarity.entity.Expenses;
import edu.fscj.cen3024c.financialclarity.entity.Income;
import edu.fscj.cen3024c.financialclarity.entity.User;

import edu.fscj.cen3024c.financialclarity.repository.UserRepository;
import org.springframework.transaction.annotation.Transactional;

import edu.fscj.cen3024c.financialclarity.repository.CategoryRepository;
import edu.fscj.cen3024c.financialclarity.repository.ExpensesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExpenseService {

    @Autowired
    private ExpensesRepository expensesRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public double getTotalExpense() {
        List<Expenses> expenses = expensesRepository.findAll();
        double totalExpense = expenses.stream().mapToDouble(Expenses::getAmount).sum();
        return totalExpense;
    }

    public double getTotalExpenseByDate(LocalDate date) {
        List<Expenses> expenses = expensesRepository.findAll();

        double totalExpense = expenses.stream()
            .filter(expense -> expense.getCreatedAt() != null && expense.getCreatedAt().toLocalDate().equals(date))
            .mapToDouble(Expenses::getAmount)
            .sum();

        return totalExpense;
    }


    public double getTotalExpenseByCategoryId(Integer categoryId) {

        List<Expenses> expenses = expensesRepository.findAll();
        double totalExpense = expenses.stream()   
            .filter(expense -> expense.getCategory().getId().equals(categoryId))
            .mapToDouble(Expenses::getAmount)
            .sum();
        return totalExpense;
    }

    public double getTotalExpenseByCategoryIdAndDate(Integer categoryId, LocalDate date) {
        List<Expenses> expenses = expensesRepository.findAll();

        double totalExpense = expenses.stream()
            .filter(expense -> expense.getCategory().getId().equals(categoryId)) 
            .filter(expense -> expense.getCreatedAt() != null && expense.getCreatedAt().toLocalDate().equals(date))
            .mapToDouble(Expenses::getAmount)
            .sum();

        return totalExpense;
    }

    public List<Expenses> findByCategoryIdAndDate(Integer categoryId, LocalDate date) {
        return expensesRepository.findAll().stream()
            .filter(expense -> expense.getCategory().getId().equals(categoryId))
            .filter(expense -> expense.getCreatedAt() != null && expense.getCreatedAt().toLocalDate().equals(date))
            .collect(Collectors.toList());
    }

    public List<Expenses> findAllByCategoryId(Integer categoryId) {
        return expensesRepository.findAll()
            .stream()
            .filter(expense -> expense.getCategory().getId().equals(categoryId))
            .collect(Collectors.toList());
    }

    public Expenses findByExpencesId(Integer expensesId) {return expensesRepository.findByExpenseId(expensesId);}
    public List<Expenses> findAll() {return expensesRepository.findAll();}
    @Transactional
    public void deleteByExpenses(Integer expensesId) {expensesRepository.deleteByExpenseId(expensesId);}
    public Expenses save(Expenses expenses ) { return expensesRepository.save(expenses); }
    public ExpensesDTO save(ExpensesDTO expensesDTO) {
        User user = userRepository.findById(expensesDTO.getUserId())
                        .orElseThrow(() -> new RuntimeException("User not found"));

        Category category = categoryRepository.findById(expensesDTO.getCategoryId()).orElseThrow(() -> new RuntimeException("Category not found"));


        // Convert ExpenseDTO to Expense entity
        Expenses expenses = new Expenses();
        expenses.setExpenseId(expensesDTO.getExpenseId());
        expenses.setUser(user);
        expenses.setCategory(category);
        expenses.setAmount(expensesDTO.getAmount());
        expenses.setName(expensesDTO.getName());

        // Save the User entity
        Expenses savedExpenses = expensesRepository.save(expenses);

        // Convert saved User entity back to UserDTO and return
        return convertToDTO(savedExpenses);
    }

    private ExpensesDTO convertToDTO(Expenses expenses) {
        return new ExpensesDTO(expenses.getExpenseId(), expenses.getUser().getId(), expenses.getCategory().getId(), expenses.getAmount(), expenses.getName(), expenses.getCreatedAt(), expenses.getUpdatedAt());
    }
}

