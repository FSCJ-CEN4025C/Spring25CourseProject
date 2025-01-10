package edu.fscj.cen3024c.financialclarity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import edu.fscj.cen3024c.financialclarity.entity.Savings;

import java.util.List;

@Repository
public interface SavingsRepository extends JpaRepository<Savings, Integer> {
    List<Savings> findAll();
    Savings findBySavingsId(Integer savingsId);
    void deleteBySavingsId(Integer savingsId);
}

