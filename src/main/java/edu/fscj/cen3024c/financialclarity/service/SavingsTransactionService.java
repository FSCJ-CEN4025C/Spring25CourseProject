package edu.fscj.cen3024c.financialclarity.service;

import edu.fscj.cen3024c.financialclarity.dto.SavingsTransactionDTO;
import edu.fscj.cen3024c.financialclarity.entity.SavingsTransaction;
import edu.fscj.cen3024c.financialclarity.entity.User;
import edu.fscj.cen3024c.financialclarity.entity.Savings;
import edu.fscj.cen3024c.financialclarity.repository.SavingsTransactionRepository;
import edu.fscj.cen3024c.financialclarity.repository.UserRepository;
import edu.fscj.cen3024c.financialclarity.repository.SavingsRepository;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SavingsTransactionService {
    @Autowired
    private SavingsTransactionRepository savingsTransactionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SavingsRepository savingsRepository;

    public List<SavingsTransaction> findAll() { return savingsTransactionRepository.findAll(); }
    public SavingsTransaction findByTransactionId(Integer transactionId) { return savingsTransactionRepository.findByTransactionId(transactionId); }

    @Transactional
    public void deleteByTransactionId(Integer transactionId){
        savingsTransactionRepository.deleteById(transactionId);
    }

    public SavingsTransaction save(SavingsTransaction savingsTransaction) { return savingsTransactionRepository.save(savingsTransaction); }

    public SavingsTransactionDTO save(SavingsTransactionDTO savingsTransactionDTO){
        User user = userRepository.findById(savingsTransactionDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        Savings savings = savingsRepository.findById(savingsTransactionDTO.getSavingsId())
                .orElseThrow(() -> new RuntimeException("Savings account not found"));

        SavingsTransaction savingsTransaction = new SavingsTransaction();
        savingsTransaction.setTransactionId(savingsTransactionDTO.getTransactionId());
        savingsTransaction.setSavings(savings);
        savingsTransaction.setAmount(savingsTransactionDTO.getAmount());
        savingsTransaction.setTransactionType(savingsTransactionDTO.getTransactionType());
        savingsTransaction.setUser(user);
        savingsTransaction.setSource(savingsTransactionDTO.getSource());
        savingsTransaction.setStatus(savingsTransactionDTO.getStatus());
        savingsTransaction.setTransactionMethod(savingsTransactionDTO.getTransactionMethod());
        savingsTransaction.setTransactionDate(savingsTransactionDTO.getTransactionDate());

        SavingsTransaction savedTransaction = savingsTransactionRepository.save(savingsTransaction);
        return convertToDTO(savedTransaction);
    }

    private SavingsTransactionDTO convertToDTO(SavingsTransaction savingsTransaction) {
        return new SavingsTransactionDTO(
            savingsTransaction.getTransactionId(),
            savingsTransaction.getSavings().getSavingsId(),
            savingsTransaction.getAmount(),
            savingsTransaction.getTransactionType(),
            savingsTransaction.getUser().getId(),
            savingsTransaction.getSource(),
            savingsTransaction.getStatus(),
            savingsTransaction.getTransactionMethod(),
            savingsTransaction.getTransactionDate(),
            savingsTransaction.getCreatedAt(),
            savingsTransaction.getUpdatedAt()
        );
    }
}
