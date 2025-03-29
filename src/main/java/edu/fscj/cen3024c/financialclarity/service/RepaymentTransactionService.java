package edu.fscj.cen3024c.financialclarity.service;

import edu.fscj.cen3024c.financialclarity.dto.RepaymentTransactionDTO;
import edu.fscj.cen3024c.financialclarity.entity.RepaymentTransaction;
import edu.fscj.cen3024c.financialclarity.entity.User;
import edu.fscj.cen3024c.financialclarity.repository.RepaymentTransactionRepository;
import edu.fscj.cen3024c.financialclarity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class RepaymentTransactionService {

    @Autowired
    private RepaymentTransactionRepository repaymentTransactionRepository;

    @Autowired
    private UserRepository userRepository;

    public List<RepaymentTransaction> findAll() {
        return repaymentTransactionRepository.findAll();
    }

    public Optional<RepaymentTransaction> findById(Integer id) {
        return repaymentTransactionRepository.findById(id);
    }

    @Transactional
    public void deleteById(Integer id) {
        repaymentTransactionRepository.deleteById(id);
    }

    public RepaymentTransaction save(
            RepaymentTransaction repaymentTransaction) {
        return repaymentTransactionRepository.save(repaymentTransaction);
    }
    public RepaymentTransactionDTO save(
            RepaymentTransactionDTO repaymentTransactionDTO) {
        User user = userRepository.findById(
                repaymentTransactionDTO.getUser().getId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        RepaymentTransaction repaymentTransaction = new RepaymentTransaction();

        repaymentTransaction.setUser(user);
        repaymentTransaction.setPlanId(repaymentTransactionDTO.getPlanId());
        repaymentTransaction.setDate(repaymentTransactionDTO.getDate());
        repaymentTransaction.setAmount(repaymentTransactionDTO.getAmount());

        RepaymentTransaction savedRepaymentTransaction =
                repaymentTransactionRepository.save(repaymentTransaction);
        return convertToDTO(savedRepaymentTransaction);
    }

    private RepaymentTransactionDTO convertToDTO(
            RepaymentTransaction repaymentTransaction) {
        return new RepaymentTransactionDTO(repaymentTransaction.getId(),
                repaymentTransaction.getUser(),
                repaymentTransaction.getPlanId(),
                repaymentTransaction.getDate(),
                repaymentTransaction.getAmount());
    }
}


