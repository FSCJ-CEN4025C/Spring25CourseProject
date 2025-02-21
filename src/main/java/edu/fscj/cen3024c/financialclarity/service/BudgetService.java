package edu.fscj.cen3024c.financialclarity.service;

import edu.fscj.cen3024c.financialclarity.dto.BudgetDTO;
import edu.fscj.cen3024c.financialclarity.entity.Budget;
import edu.fscj.cen3024c.financialclarity.entity.User;
import edu.fscj.cen3024c.financialclarity.repository.BudgetRepository;
import edu.fscj.cen3024c.financialclarity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BudgetService {

    @Autowired
    private BudgetRepository budgetRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Budget> findAll() { return budgetRepository.findAll();}
    public Budget findById(Integer budgetId) { 
        return budgetRepository.findByBudgetId(budgetId);
    }

    @Transactional
    public void deleteByBudgetId(Integer budgetId) { 
        budgetRepository.deleteByBudgetId(budgetId);
    }

    public Budget save(Budget budget) { return budgetRepository.save(budget); }
    public BudgetDTO save(BudgetDTO budgetDTO) {
        User user = userRepository.findById(budgetDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        Budget budget = new Budget();
        
        budget.setUserId(user.getId());
        budget.setCategoryId(budgetDTO.getCategoryId());
        budget.setAmount(budgetDTO.getAmount());
        budget.setPeriod(budgetDTO.getPeriod());
        budget.setStartDate(budgetDTO.getStartDate());
        budget.setEndDate(budgetDTO.getEndDate());

        Budget savedBudget = budgetRepository.save(budget);
        return convertToDTO(savedBudget);
    }

    private BudgetDTO convertToDTO(Budget budget) {
        return new BudgetDTO(budget.getId(),
                             budget.getUserId(), 
                             budget.getCategoryId(), 
                             budget.getAmount(), 
                             budget.getPeriod(), 
                             budget.getStartDate(), 
                             budget.getEndDate());
    }
}
