package edu.fscj.cen3024c.financialclarity.repository;

import edu.fscj.cen3024c.financialclarity.entity.Budget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BudgetRepository extends JpaRepository<Budget, Integer> {
    // Find all Users
    List<Budget> findAll();
    Budget findByBudgetName(String budgetName);
    void deleteByBudgetName(String budgetName);
}
