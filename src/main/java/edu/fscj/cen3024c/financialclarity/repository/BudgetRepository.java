package edu.fscj.cen3024c.financialclarity.repository;

import edu.fscj.cen3024c.financialclarity.entity.Budget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BudgetRepository extends JpaRepository<Budget, Integer> {
    // Find all Budgets
    List<Budget> findAll();
//    Optional<Budget> findById(Integer id);
    void deleteById(Integer id);
}
