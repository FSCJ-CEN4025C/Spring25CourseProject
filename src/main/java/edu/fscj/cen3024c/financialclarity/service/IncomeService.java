package edu.fscj.cen3024c.financialclarity.service;

import edu.fscj.cen3024c.financialclarity.dto.IncomeDTO;
import edu.fscj.cen3024c.financialclarity.entity.Income;

import edu.fscj.cen3024c.financialclarity.entity.User;
import edu.fscj.cen3024c.financialclarity.entity.Category;
import edu.fscj.cen3024c.financialclarity.repository.CategoryRepository;
import edu.fscj.cen3024c.financialclarity.repository.IncomeRepository;
import edu.fscj.cen3024c.financialclarity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
public class IncomeService {
    @Autowired
    private IncomeRepository incomeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public double getTotalIncome() {
        List<Income> Incomes = incomeRepository.findAll();
        double totalIncome = Incomes.stream().mapToDouble(Income::getAmount).sum();   
        return totalIncome;
    }

    public List<Income> findAll() { return incomeRepository.findAll(); }
    public Income findIncomeById(Integer incomeId) {return incomeRepository.findByIncomeId(incomeId);}
    @Transactional
    public void deleteById(int id) {incomeRepository.deleteById(id);}

    public IncomeDTO save(IncomeDTO incomeDTO) {
        User user = userRepository.findById(incomeDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        Category category = categoryRepository.findById(incomeDTO.getCategoryId()).orElseThrow(() -> new RuntimeException("Category not found"));
        Income income = new Income();
        income.setIncomeId(incomeDTO.getIncomeId());
        income.setUser(user);
        income.setCategory(category);
        income.setName(incomeDTO.getName());
        income.setAmount(incomeDTO.getAmount());
        Income savedIncome = incomeRepository.save(income);
        return convertToDTO(savedIncome);
    }

    private IncomeDTO convertToDTO(Income income) {
        return new IncomeDTO(income.getIncomeId(), income.getUser().getId(), income.getCategory().getId(), income.getAmount(), income.getName(), income.getCreatedAt(), income.getUpdatedAt());
    }

}
