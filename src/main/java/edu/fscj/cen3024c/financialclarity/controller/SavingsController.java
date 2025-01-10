package edu.fscj.cen3024c.financialclarity.controller;

import edu.fscj.cen3024c.financialclarity.dto.SavingsDTO;
import edu.fscj.cen3024c.financialclarity.entity.Savings;
import edu.fscj.cen3024c.financialclarity.entity.User;
import edu.fscj.cen3024c.financialclarity.repository.UserRepository;
import edu.fscj.cen3024c.financialclarity.service.SavingsService;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.profiler.Profiler;
import org.slf4j.profiler.TimeInstrument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/savings")
public class SavingsController {
    @Autowired
    private SavingsService savingsService;

    @Autowired
    private UserRepository userRepository;

    private static final Logger logger = LoggerFactory.getLogger(SavingsController.class);

    private SavingsDTO convertToDTO(Savings savings) {
        return new SavingsDTO(savings.getSavingsId(), savings.getSavingsamount(), savings.getUser().getId(), savings.getDescription());
    }

    @CrossOrigin(origins = {"http://example.com", "http://localhost"})
    @GetMapping("/{savingsId}")
    public ResponseEntity<Savings> getSavingsBySavingsId(@PathVariable Integer savingsId) {
        Savings savings = savingsService.findBySavingsById(savingsId);
        return ResponseEntity.ok(savings);
    }

    @PostMapping
    public ResponseEntity<SavingsDTO> createSavings(@RequestBody SavingsDTO savingsDTO) {
        Profiler profiler = new Profiler("createUser");
        profiler.start("Create User");

        SavingsDTO savedSavings = savingsService.save(savingsDTO);
        logger.info("Savings Amount: {}", savedSavings.getSavingsAmount());

        TimeInstrument ti = profiler.stop();
        ti.print();

        return ResponseEntity.status(HttpStatus.CREATED).body(savedSavings);
    }

    @PutMapping("savingID")
    public ResponseEntity<SavingsDTO> updateSavings(@PathVariable Integer savingsID, @RequestBody SavingsDTO savingsDTO) {
        Savings savings = savingsService.findBySavingsById(savingsID);
        if (savings != null) {
            User user = userRepository.findById(savingsDTO.getUserid())
                    .orElseThrow(() -> new RuntimeException("User not found"));
            savings.setSavingsamount(savingsDTO.getSavingsAmount());
            savings.setUser(user);
            savings.setDescription(savingsDTO.getDescription());
            logger.info("A users savings has been updated: {}", savingsID);
            return new ResponseEntity<>(convertToDTO(savingsService.save(savings)), HttpStatus.OK);
        }else{
            logger.info("Savings ID not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping
    public ResponseEntity<SavingsDTO> deleteSavings(@PathVariable Integer savingsID) {
        savingsService.deleteBySavingsId(savingsID);
        logger.info("Savings ID: {} has been deleted!", savingsID);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
