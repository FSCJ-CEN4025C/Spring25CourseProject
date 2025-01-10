package edu.fscj.cen3024c.financialclarity.controller;


import edu.fscj.cen3024c.financialclarity.dto.ExpensesDTO;
import edu.fscj.cen3024c.financialclarity.dto.UserDTO;
import edu.fscj.cen3024c.financialclarity.entity.Expenses;
import edu.fscj.cen3024c.financialclarity.entity.User;
import edu.fscj.cen3024c.financialclarity.repository.UserRepository;
import edu.fscj.cen3024c.financialclarity.service.ExpenseService;


import edu.fscj.cen3024c.financialclarity.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/expenses")
public class ExpensesController {

    @Autowired
    private ExpenseService expenseService;

    @Autowired
    private UserRepository userRepository;

    private ExpensesDTO convertToDTO(Expenses expenses) {
        return new ExpensesDTO(expenses.getExpenseId(), expenses.getUser().getId(), expenses.getAmount(), expenses.getName());
    }
    @GetMapping
    public List<ExpensesDTO> getAllUsers() {
        List<Expenses> expenses = expenseService.findAll();
        return expenses.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @CrossOrigin(origins = {"http://example.com", "http://localhost"})
    @GetMapping("/{expensesId}")
    public ResponseEntity<Expenses> getIncome(@PathVariable Integer expensesId) {

        Expenses expenses = expenseService.findByExpencesId(expensesId);
        return ResponseEntity.ok(expenses);
    }

    @PostMapping()
    public ResponseEntity<ExpensesDTO> createExpenses(@RequestBody ExpensesDTO expensesDTO) {
        ExpensesDTO savedExpense = expenseService.save(expensesDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedExpense);
    }

    @PutMapping("{expensesId}")
    public ResponseEntity<ExpensesDTO> updateExpenses(@PathVariable Integer expensesId, @RequestBody ExpensesDTO expensesDTO) {
        Expenses expenses = expenseService.findByExpencesId(expensesId);
        if  (expenses != null) {
            User user = userRepository.findById(expensesDTO.getUserId())
                    .orElseThrow(() -> new RuntimeException("User not found"));;
            expenses.setExpenseId(expensesDTO.getExpenseId());
            expenses.setUser(user);
            expenses.setAmount(expensesDTO.getAmount());
            expenses.setName(expensesDTO.getName());
            Expenses updatedExpenses = expenseService.save(expenses);
            return new ResponseEntity<>(convertToDTO(updatedExpenses), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("{expensesId}")
    public ResponseEntity<ExpensesDTO> deleteExpenses(@PathVariable Integer expensesId) {
        expenseService.deleteByExpenses(expensesId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
