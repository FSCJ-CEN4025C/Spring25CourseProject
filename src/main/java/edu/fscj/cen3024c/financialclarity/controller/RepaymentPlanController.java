package edu.fscj.cen3024c.financialclarity.controller;

import edu.fscj.cen3024c.financialclarity.dto.RepaymentPlanDTO;
import edu.fscj.cen3024c.financialclarity.entity.RepaymentPlan;
import edu.fscj.cen3024c.financialclarity.entity.User;
import edu.fscj.cen3024c.financialclarity.repository.UserRepository;
import edu.fscj.cen3024c.financialclarity.service.RepaymentPlanService;


import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/repaymentPlan")
public class RepaymentPlanController {
    @Autowired
    private RepaymentPlanService repaymentPlanService;

    @Autowired
    private UserRepository userRepository;


    // Convert RepaymentPlan to DTO
    private RepaymentPlanDTO convertToDTO(RepaymentPlan repaymentPlan) {
        return new RepaymentPlanDTO(
                repaymentPlan.getPlanId(),
                repaymentPlan.getUser().getId(),
                repaymentPlan.getTotalExpense(),
                repaymentPlan.getPayment(),
                repaymentPlan.getPlanName(),
                repaymentPlan.getTimeLine(),
                repaymentPlan.getCategory()
        );
    }

    @CrossOrigin(origins = {"http://example.com", "http://localhost"})
    @GetMapping("/{planId}")
    public ResponseEntity<RepaymentPlanDTO> getRepaymentByPlanName(@PathVariable String planName) {
        RepaymentPlan repaymentPlan = repaymentPlanService.findByPlanName(planName);
        if (repaymentPlan != null) {
            return ResponseEntity.ok(convertToDTO(repaymentPlan));
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{planName}")
    public ResponseEntity<RepaymentPlanDTO> updatePlan(@PathVariable String planName, @RequestBody RepaymentPlanDTO planDTO) {
        RepaymentPlan repaymentPlan = repaymentPlanService.findByPlanName(planName);
        if (repaymentPlan != null) {
            User user = userRepository.findById(planDTO.getUserId())
                    .orElseThrow(() -> new RuntimeException("User not found"));
            repaymentPlan.setUser(user);
            repaymentPlan.setPlanName(planDTO.getName());
            repaymentPlan.setTotalExpense(planDTO.getTotalExpense());
            repaymentPlan.setTimeLine(repaymentPlan.getTimeLine());
            RepaymentPlan updatedPlan = repaymentPlanService.save(repaymentPlan);
            return new ResponseEntity<>(convertToDTO(updatedPlan), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<RepaymentPlanDTO> createRepaymentPlan(@RequestBody RepaymentPlanDTO repaymentPlanDTO) {
        RepaymentPlanDTO savedPlan = repaymentPlanService.save(repaymentPlanDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPlan);
    }


    @DeleteMapping("/{planName}")
    public ResponseEntity<RepaymentPlanDTO> deleteRepaymentPlan(@PathVariable String planName) {
        repaymentPlanService.deleteByPlanName(planName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}