package edu.fscj.cen3024c.financialclarity.controller;

import edu.fscj.cen3024c.financialclarity.dto.RepaymentTransactionDTO;
import edu.fscj.cen3024c.financialclarity.entity.RepaymentTransaction;
import edu.fscj.cen3024c.financialclarity.entity.User;
import edu.fscj.cen3024c.financialclarity.repository.UserRepository;
import edu.fscj.cen3024c.financialclarity.service.RepaymentTransactionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.slf4j.profiler.Profiler;
import org.slf4j.profiler.TimeInstrument;

@RestController
@RequestMapping("/repayment_transaction")
public class RepaymentTransactionController {
    @Autowired
    private RepaymentTransactionService repaymentTransactionService;

    @Autowired
    private UserRepository userRepository;

    private static final Logger logger =
            LoggerFactory.getLogger(RepaymentTransactionController.class);

    private RepaymentTransactionDTO convertToDTO(
            RepaymentTransaction repaymentTransaction) {
        return new RepaymentTransactionDTO(repaymentTransaction.getId(),
                repaymentTransaction.getUser(),
                repaymentTransaction.getPlanId(),
                repaymentTransaction.getDate(),
                repaymentTransaction.getAmount());
    }

    @CrossOrigin(origins = {"http://example.com", "http://localhost"})

    @GetMapping
    public List<RepaymentTransactionDTO> getAllRepaymentTransactions() {
        List<RepaymentTransaction> repaymentTransactions =
                repaymentTransactionService.findAll();
        logger.error("Found all repayment transactions");
        return repaymentTransactions.stream()
                .map(this::convertToDTO).collect(Collectors.toList());
    }

    @GetMapping("/{repaymentTransactionId}")
    public ResponseEntity<RepaymentTransactionDTO> getRepaymentTransactionDTOById(
            @PathVariable Integer id) {
        Optional<RepaymentTransaction> optionalRepaymentTransaction =
                repaymentTransactionService.findById(id);
        RepaymentTransaction repaymentTransaction =
                optionalRepaymentTransaction.get();
        RepaymentTransactionDTO repaymentTransactionDTO =
                convertToDTO(repaymentTransaction);
        logger.error("Found repayment transaction {}", id);
        return ResponseEntity.ok(repaymentTransactionDTO);
    }

    @PostMapping("/createRepaymentTransaction")
    public ResponseEntity<RepaymentTransactionDTO> createRepaymentTransaction(
            @RequestBody RepaymentTransactionDTO repaymentTransactionDTO) {
        // start the profiler
        Profiler profiler = new Profiler("createUser");
        profiler.start("Create User");

        RepaymentTransactionDTO savedRepaymentTransaction =
                repaymentTransactionService.save(repaymentTransactionDTO);
        logger.info("A new repayment transaction has been added: {}",
                savedRepaymentTransaction.getId());

        // stop the profiler
        TimeInstrument ti = profiler.stop();
        ti.print();

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedRepaymentTransaction);
    }

    @PutMapping("/updateRepaymentTransaction/{id}")
    public ResponseEntity<RepaymentTransactionDTO> updateRepaymentTransaction(
            @PathVariable Integer id,
            @RequestBody String username,
            @RequestBody RepaymentTransactionDTO repaymentTransactionDTO) {
        Optional<RepaymentTransaction> optionalRepaymentTransaction =
                repaymentTransactionService.findById(id);
        RepaymentTransaction repaymentTransaction =
                optionalRepaymentTransaction.get();
        if (repaymentTransaction != null) {
            User user = userRepository.findByUsername(username);
            if (user != null) {
                repaymentTransaction.setUser(
                        repaymentTransactionDTO.getUser());
                repaymentTransaction.setPlanId(
                        repaymentTransactionDTO.getPlanId());
                repaymentTransaction.setDate(
                        repaymentTransactionDTO.getDate());
                repaymentTransaction.setAmount(
                        repaymentTransactionDTO.getAmount());
                RepaymentTransaction updatedRepaymentTransaction =
                        repaymentTransactionService.save(repaymentTransaction);
                logger.info("A repayment transaction has been updated: {}", id);
                return new ResponseEntity<>(
                        convertToDTO(updatedRepaymentTransaction),
                        HttpStatus.OK);

            } else {
                logger.error("Update repayment transaction failed, " +
                        "user not found: {}", username);
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

        } else {
            logger.error("Update repayment transaction failed, " +
                    "not found: {}", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<RepaymentTransactionDTO> deleteRepaymentTransaction(
            @PathVariable Integer id) {
        repaymentTransactionService.deleteById(id);
        logger.error("Deleted repayment transaction {}", id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
