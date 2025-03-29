package edu.fscj.cen3024c.financialclarity.repository;

import edu.fscj.cen3024c.financialclarity.entity.RepaymentTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepaymentTransactionRepository extends JpaRepository<RepaymentTransaction, Integer> {
    // Find all
    List<RepaymentTransaction> findAll();

    void deleteById(Integer id);
}
