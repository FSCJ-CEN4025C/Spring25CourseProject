package edu.fscj.cen3024c.financialclarity.repository;

import edu.fscj.cen3024c.financialclarity.entity.Expenses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpensesRepository extends JpaRepository<Expenses, Integer> {
    // Find all Users
    List<Expenses> findAll();

    Expenses findByExpenseId(Integer expenseId);
    void deleteByExpenseId(Integer expenseId);
}
