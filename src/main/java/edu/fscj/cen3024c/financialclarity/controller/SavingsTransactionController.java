package edu.fscj.cen3024c.financialclarity.controller;

import edu.fscj.cen3024c.financialclarity.dto.SavingsTransactionDTO;
import edu.fscj.cen3024c.financialclarity.entity.SavingsTransaction;
import edu.fscj.cen3024c.financialclarity.entity.Savings;
import edu.fscj.cen3024c.financialclarity.entity.User;
import edu.fscj.cen3024c.financialclarity.service.SavingsTransactionService;
import edu.fscj.cen3024c.financialclarity.repository.UserRepository;
import edu.fscj.cen3024c.financialclarity.repository.SavingsRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/savings-transactions")
public class SavingsTransactionController {

    @Autowired
    private SavingsTransactionService savingsTransactionService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SavingsRepository savingsRepository;

    private static final Logger logger = LoggerFactory.getLogger(SavingsTransactionController.class);

    private SavingsTransactionDTO convertToDTO(SavingsTransaction transaction) {
        return new SavingsTransactionDTO(
                transaction.getTransactionId(),
                transaction.getSavings().getSavingsId(),
                transaction.getAmount(),
                transaction.getTransactionType(),
                transaction.getUser().getId(),
                transaction.getSource(),
                transaction.getStatus(),
                transaction.getTransactionMethod(),
                transaction.getTransactionDate(),
                transaction.getCreatedAt(),
                transaction.getUpdatedAt()
        );
    }

    @CrossOrigin(origins = {"http://example.com", "http://localhost"})
    @GetMapping("/{transactionId}")
    public ResponseEntity<SavingsTransactionDTO> getTransactionById(@PathVariable Integer transactionId) {
        SavingsTransaction transaction = savingsTransactionService.findByTransactionId(transactionId);
        return ResponseEntity.ok(convertToDTO(transaction));
    }

    @GetMapping
    public ResponseEntity<List<SavingsTransactionDTO>> getAllTransactions() {
        List<SavingsTransactionDTO> transactions = savingsTransactionService.findAll().stream()
                .map(this::convertToDTO)
                .toList();
        return ResponseEntity.ok(transactions);
    }

    @PostMapping
    public ResponseEntity<SavingsTransactionDTO> createTransaction(@RequestBody SavingsTransactionDTO transactionDTO) {
        User user = userRepository.findById(transactionDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        Savings savings = savingsRepository.findById(transactionDTO.getSavingsId())
                .orElseThrow(() -> new RuntimeException("Savings account not found"));

        SavingsTransaction transaction = new SavingsTransaction();
        transaction.setSavings(savings);
        transaction.setAmount(transactionDTO.getAmount());
        transaction.setTransactionType(transactionDTO.getTransactionType());
        transaction.setUser(user);
        transaction.setSource(transactionDTO.getSource());
        transaction.setStatus(transactionDTO.getStatus());
        transaction.setTransactionMethod(transactionDTO.getTransactionMethod());
        transaction.setTransactionDate(transactionDTO.getTransactionDate());

        SavingsTransaction savedTransaction = savingsTransactionService.save(transaction);
        logger.info("New savings transaction created: {}", savedTransaction.getTransactionId());

        return ResponseEntity.status(HttpStatus.CREATED).body(convertToDTO(savedTransaction));
    }

    @PutMapping("/{transactionId}")
    public ResponseEntity<SavingsTransactionDTO> updateTransaction(
            @PathVariable Integer transactionId, @RequestBody SavingsTransactionDTO transactionDTO) {
        SavingsTransaction transaction = savingsTransactionService.findByTransactionId(transactionId);
        if (transaction != null) {
            transaction.setAmount(transactionDTO.getAmount());
            transaction.setTransactionType(transactionDTO.getTransactionType());
            transaction.setSource(transactionDTO.getSource());
            transaction.setStatus(transactionDTO.getStatus());
            transaction.setTransactionMethod(transactionDTO.getTransactionMethod());
            transaction.setTransactionDate(transactionDTO.getTransactionDate());

            SavingsTransaction updatedTransaction = savingsTransactionService.save(transaction);
            logger.info("Updated savings transaction: {}", transactionId);
            return ResponseEntity.ok(convertToDTO(updatedTransaction));
        } else {
            logger.warn("Transaction ID not found: {}", transactionId);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{transactionId}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable Integer transactionId) {
        savingsTransactionService.deleteByTransactionId(transactionId);
        logger.info("Deleted savings transaction: {}", transactionId);
        return ResponseEntity.noContent().build();
    }
}
