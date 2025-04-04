package edu.fscj.cen3024c.financialclarity.service;

import edu.fscj.cen3024c.financialclarity.dto.SavingsDTO;
import edu.fscj.cen3024c.financialclarity.entity.Savings;

import edu.fscj.cen3024c.financialclarity.entity.User;
import edu.fscj.cen3024c.financialclarity.entity.Category;
import edu.fscj.cen3024c.financialclarity.repository.SavingsRepository;
import edu.fscj.cen3024c.financialclarity.repository.UserRepository;
import edu.fscj.cen3024c.financialclarity.repository.CategoryRepository;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SavingsService {
    @Autowired
    private SavingsRepository savingsRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Savings> findAll() {return savingsRepository.findAll();}
    public Savings findBySavingsById(Integer savingsId) {return savingsRepository.findBySavingsId(savingsId);}

    @Transactional
    public void deleteBySavingsId(Integer savingsId){
        savingsRepository.deleteById(savingsId);
    }
    public Savings save(Savings savings) {return savingsRepository.save(savings);}

    public SavingsDTO save(SavingsDTO savingsDTO){
        Category category = categoryRepository.findById(savingsDTO.getCategoryId()).orElseThrow(() -> new RuntimeException("Category not found"));
        User user = userRepository.findById(savingsDTO.getUserid())
                .orElseThrow(() -> new RuntimeException("User not found"));
        Savings savings = new Savings();
        savings.setSavingsId(savingsDTO.getSavingsId());
        savings.setCategory(category);
        savings.setSavingsamount(savingsDTO.getSavingsAmount());
        savings.setDescription(savingsDTO.getDescription());
        savings.setUser(user);

        Savings savedSavings = savingsRepository.save(savings);
        return convertToDTO(savedSavings);
    }

    private SavingsDTO convertToDTO(Savings savings) {
        return new SavingsDTO(savings.getSavingsId(), savings.getSavingsamount(), savings.getCategory().getId(), savings.getUser().getId(), savings.getDescription(), savings.getCreatedAt(), savings.getUpdatedAt());
    }
}
