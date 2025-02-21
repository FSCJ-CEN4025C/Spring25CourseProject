package edu.fscj.cen3024c.financialclarity.service;

import edu.fscj.cen3024c.financialclarity.dto.CategoryDTO;
import edu.fscj.cen3024c.financialclarity.entity.Category;
import edu.fscj.cen3024c.financialclarity.entity.User;
import edu.fscj.cen3024c.financialclarity.repository.CategoryRepository;
import edu.fscj.cen3024c.financialclarity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
 
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Category> findAll() { return categoryRepository.findAll();}

    public Optional<Category> findById(Integer categoryId) {
        return categoryRepository.findById(categoryId);
    }

    @Transactional
    public void deleteById(Integer categoryId) { 
        categoryRepository.deleteById(categoryId);
    }   

    public Category save(Category category) { 
        return categoryRepository.save(category); 
    }

    public CategoryDTO save(CategoryDTO categoryDTO) {
        User user = userRepository.findById(categoryDTO.getUser().getId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        Category category = new Category();
        
        category.setUser(user);
        category.setName(categoryDTO.getName());
        category.setType(categoryDTO.getType());

        Category savedCategory = categoryRepository.save(category);
        return convertToDTO(savedCategory);
    }

    private CategoryDTO convertToDTO(Category category) {
        return new CategoryDTO(category.getId(),
                               category.getUser(), 
                               category.getName(), 
                               category.getType());
    }
}
