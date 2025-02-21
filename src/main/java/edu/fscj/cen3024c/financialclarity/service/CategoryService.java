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

@Service
public class CategoryService {
 
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Category> findAll() { return categoryRepository.findAll();}

    public Category findById(Integer categoryId) { 
        return categoryRepository.findByCategoryId(categoryId);
    }

    @Transactional
    public void deleteById(Integer categoryId) { 
        categoryRepository.deleteByCategoryId(categoryId);
    }   

    public Category save(Category category) { 
        return categoryRepository.save(category); 
    }

    public CategoryDTO save(CategoryDTO categoryDTO) {
        User user = userRepository.findById(categoryDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        Category category = new Category();
        
        category.setUserId(user.getId());
        category.setName(categoryDTO.getName());
        category.setType(categoryDTO.getType());

        Category savedCategory = categoryRepository.save(category);
        return convertToDTO(savedCategory);
    }

    private CategoryDTO convertToDTO(Category category) {
        return new CategoryDTO(category.getId(),
                               category.getUserId(), 
                               category.getName(), 
                               category.getType());
    }
}
