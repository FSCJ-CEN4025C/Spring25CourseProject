package edu.fscj.cen3024c.financialclarity.controller;

import edu.fscj.cen3024c.financialclarity.dto.BudgetDTO;
import edu.fscj.cen3024c.financialclarity.entity.Budget;
import edu.fscj.cen3024c.financialclarity.entity.User;
import edu.fscj.cen3024c.financialclarity.repository.UserRepository;
import edu.fscj.cen3024c.financialclarity.service.BudgetService;

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
@RequestMapping("/budget")
public class BudgetController {

    @Autowired
    private BudgetService budgetService;

    @Autowired
    private UserRepository userRepository;

    private static final Logger logger =
            LoggerFactory.getLogger(BudgetController.class);

    private BudgetDTO convertToDTO(Budget budget) {
        return new BudgetDTO(budget.getId(),
                             budget.getUser(), 
                             budget.getCategoryId(), 
                             budget.getAmount(), 
                             budget.getPeriod(), 
                             budget.getStartDate(), 
                             budget.getEndDate());
    }

    @CrossOrigin(origins = {"http://example.com", "http://localhost"})

    @GetMapping
    public List<BudgetDTO> getAllBudgets() {
        List<Budget> budgets = budgetService.findAll();
        logger.error("Found all budgets");
        return budgets.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @GetMapping("/{budgetId}")
    public ResponseEntity<BudgetDTO> getBudgetById(@PathVariable Integer budgetId) {
        Optional<Budget> optionalBudget = budgetService.findById(budgetId);
        Budget budget = optionalBudget.get();
        BudgetDTO budgetDTO = convertToDTO(budget);
        logger.error("Found budget {}", budgetId);
        return ResponseEntity.ok(budgetDTO);
    }

    @PostMapping("/createBudget")
    public ResponseEntity<BudgetDTO> createBudget(@RequestBody BudgetDTO budgetDTO) {
        // start the profiler
        Profiler profiler = new Profiler("createUser");
        profiler.start("Create User");

        BudgetDTO savedBudget = budgetService.save(budgetDTO);
        logger.info("A new budget has been added: {}", savedBudget.getId());

        // stop the profiler
        TimeInstrument ti = profiler.stop();
        ti.print();

        return ResponseEntity.status(HttpStatus.CREATED).body(savedBudget);
    }

    @PutMapping("/updateBudget/{budgetId}")
    public ResponseEntity<BudgetDTO> updateBudget(
            @PathVariable Integer budgetId,
            @RequestBody String username,
            @RequestBody BudgetDTO budgetDTO) {
        Optional<Budget> optionalBudget = budgetService.findById(budgetId);
        Budget budget = optionalBudget.get();
        if (budget != null) {
            User user = userRepository.findByUsername(username);
            if (user != null) {
                budget.setUser(budgetDTO.getUser());
                budget.setCategoryId(budgetDTO.getCategoryId());
                budget.setAmount(budgetDTO.getAmount());
                budget.setPeriod(budgetDTO.getPeriod());
                budget.setStartDate(budgetDTO.getStartDate());
                budget.setEndDate(budgetDTO.getEndDate());
                Budget updatedBudget = budgetService.save(budget);
                logger.info("A budget has been updated: {}", budgetId);
                return new ResponseEntity<>(convertToDTO(updatedBudget), HttpStatus.OK);
                
            } else {
                logger.error("Update budget failed, user not found: {}", username);
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            
        } else {
            logger.error("Update budget failed, not found: {}", budgetId);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("{budgetId}")
    public ResponseEntity<BudgetDTO> deleteBudget(@PathVariable Integer budgetId) {
        budgetService.deleteByBudgetId(budgetId);
        logger.error("Deleted budget {}", budgetId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
