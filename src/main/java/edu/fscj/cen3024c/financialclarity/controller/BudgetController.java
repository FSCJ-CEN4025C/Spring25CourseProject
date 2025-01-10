package edu.fscj.cen3024c.financialclarity.controller;

import edu.fscj.cen3024c.financialclarity.dto.BudgetDTO;
import edu.fscj.cen3024c.financialclarity.entity.Budget;
import edu.fscj.cen3024c.financialclarity.entity.User;
import edu.fscj.cen3024c.financialclarity.repository.UserRepository;
import edu.fscj.cen3024c.financialclarity.service.BudgetService;

import edu.fscj.cen3024c.financialclarity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.slf4j.profiler.Profiler;
import org.slf4j.profiler.TimeInstrument;


@RestController
@RequestMapping("/budget")
public class BudgetController {

    @Autowired
    private BudgetService budgetService;

    @Autowired
    private UserRepository userRepository;

    private static final Logger logger = LoggerFactory.getLogger(BudgetController.class);

    private BudgetDTO convertToDTO(Budget budget) {
        return new BudgetDTO(budget.getId(), budget.getUser().getId(), budget.getBudgetName(), budget.getTimeCreate());
    }

    @CrossOrigin(origins = {"http://example.com", "http://localhost"})

    @GetMapping
    public List<BudgetDTO> getAllBudgets() {
        List<Budget> budgets = budgetService.findAll();
        logger.error("Found all budgets");
        return budgets.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @GetMapping("/{budgetName}")
    public ResponseEntity<BudgetDTO> getBudgetById(@PathVariable String budgetName) {
        Budget budget = budgetService.findByBudgetName(budgetName);
        BudgetDTO budgetDTO = convertToDTO(budget);
        logger.error("Found budget {}", budgetName);
        return ResponseEntity.ok(budgetDTO);
    }

    @PostMapping()
    public ResponseEntity<BudgetDTO> createBudget(@RequestBody BudgetDTO budgetDTO) {
        // start the profiler
        Profiler profiler = new Profiler("createUser");
        profiler.start("Create User");

        BudgetDTO savedBudget = budgetService.save(budgetDTO);
        logger.info("A new budget has been added: {}", savedBudget.getBudgetName());

        // stop the profiler
        TimeInstrument ti = profiler.stop();
        ti.print();

        return ResponseEntity.status(HttpStatus.CREATED).body(savedBudget);
    }

    @PutMapping("/{budgetName}")
    public ResponseEntity<BudgetDTO> updateBudget(@PathVariable String budgetName, @RequestBody BudgetDTO budgetDTO) {
        Budget budget = budgetService.findByBudgetName(budgetName);
        if (budget != null) {
            User user = userRepository.findById(budgetDTO.getUserId())
                    .orElseThrow(() -> new RuntimeException("User not found"));
            budget.setBudgetName(budgetDTO.getBudgetName());
            budget.setUser(user);
            Budget updatedBudget = budgetService.save(budget);
            logger.info("A budget has been updated: {}", budgetName);
            return new ResponseEntity<>(convertToDTO(updatedBudget), HttpStatus.OK);
        } else {
            logger.error("Update budget failed, not found: {}", budgetName);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("{budgetName}")
    public ResponseEntity<BudgetDTO> deleteBudget(@PathVariable String budgetName) {
        budgetService.deleteByBudgetName(budgetName);
        logger.error("Deleted budget {}", budgetName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
