package edu.fscj.cen3024c.financialclarity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import edu.fscj.cen3024c.financialclarity.entity.SavingsTransaction;

import java.util.List;

@Repository
public interface SavingsTransactionRepository extends JpaRepository<SavingsTransaction, Integer> {
    List<SavingsTransaction> findAll();
    SavingsTransaction findByTransactionId(Integer transactionId);

    void deleteByTransactionId(Integer transactionId);
}

