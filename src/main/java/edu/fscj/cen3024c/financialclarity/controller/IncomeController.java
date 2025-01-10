package edu.fscj.cen3024c.financialclarity.controller;

import edu.fscj.cen3024c.financialclarity.dto.IncomeDTO;
import edu.fscj.cen3024c.financialclarity.entity.Income;
import edu.fscj.cen3024c.financialclarity.entity.User;
import edu.fscj.cen3024c.financialclarity.repository.UserRepository;
import edu.fscj.cen3024c.financialclarity.service.IncomeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/income")
public class IncomeController {
    @Autowired
    private IncomeService incomeService;

    @Autowired
    private UserRepository userRepository;

    private IncomeDTO convertToDTO(Income income) {
        return new IncomeDTO(income.getIncomeId(), income.getUser().getId(), income.getAmount(), income.getName());
    }

    @GetMapping()
    public List<IncomeDTO> getAllIncomes() {
        List<Income> income = incomeService.findAll();
        return income.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @CrossOrigin(origins = {"http://example.com", "http://localhost"})
    @GetMapping("/{incomeId}")
    public ResponseEntity<IncomeDTO> getIncome(@PathVariable int incomeId) {
        Income income = incomeService.findIncomeById(incomeId);
        IncomeDTO incomeDTO = convertToDTO(income);
        return ResponseEntity.ok(incomeDTO);
    }

    @PostMapping()
    public ResponseEntity<IncomeDTO> getIncome(@RequestBody IncomeDTO incomeDTO) {
        IncomeDTO savedIncomeDTO = incomeService.save(incomeDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedIncomeDTO);
    }

    @PutMapping("/{incomeId}")
    public ResponseEntity<IncomeDTO> updateIncome(@PathVariable int incomeId, @RequestBody IncomeDTO incomeDTO) {
        Income income = incomeService.findIncomeById(incomeId);
        if (income != null) {
            User user = userRepository.findById(incomeDTO.getUserId())
                    .orElseThrow(() -> new RuntimeException("User not found"));

            income.setAmount(incomeDTO.getAmount());
            income.setName(incomeDTO.getName());
            income.setUser(user);
            IncomeDTO updatedIncome = incomeService.save(convertToDTO(income));
            return new ResponseEntity<>(updatedIncome, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{incomeId}")
    public ResponseEntity<IncomeDTO> deleteIncome(@PathVariable int incomeId) {
        incomeService.deleteById(incomeId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
