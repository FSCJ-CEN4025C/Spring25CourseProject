package edu.fscj.cen3024c.financialclarity.controller;

import edu.fscj.cen3024c.financialclarity.dto.CategoryDTO;
import edu.fscj.cen3024c.financialclarity.entity.Category;
import edu.fscj.cen3024c.financialclarity.entity.User;
import edu.fscj.cen3024c.financialclarity.service.CategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);

    private CategoryDTO convertToDTO(Category category) {
        return new CategoryDTO(category.getId(),
                             category.getUser(), 
                             category.getName(), 
                             category.getType());
    }

    @CrossOrigin(origins = {"http://example.com", "http://localhost"})

    @GetMapping
    public List<CategoryDTO> getAllCategories() {
        List<Category> categories = categoryService.findAll();
        logger.error("Found all categories");
        return categories.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable Integer categoryId) {
        Optional<Category> optionalCategory = categoryService.findById(categoryId);
        Category category = optionalCategory.get();
        CategoryDTO categoryDTO = convertToDTO(category);
        logger.error("Found category {}", categoryId);
        return ResponseEntity.ok(categoryDTO);
    }

    @PostMapping("/createCategory")
    public ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setUser(categoryDTO.getUser());
        category.setName(categoryDTO.getName());
        category.setType(categoryDTO.getType());
        categoryService.save(category);
        logger.error("Created category {}", category.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(convertToDTO(category));
    }

    @PutMapping("/updateCategory/{categoryName}")
    public ResponseEntity<CategoryDTO> updateCategory(@PathVariable Integer categoryId, @RequestBody CategoryDTO categoryDTO) {
        Optional<Category> optionalCategory = categoryService.findById(categoryId);
        Category category = optionalCategory.get();
        category.setUser(categoryDTO.getUser());
        category.setName(categoryDTO.getName());
        category.setType(categoryDTO.getType());
        categoryService.save(category);
        logger.error("Updated category {}", category.getId());
        return ResponseEntity.ok(convertToDTO(category));
    }

    @DeleteMapping("/deleteCategory/{categoryId}")
    public ResponseEntity<String> deleteCategory(@PathVariable Integer categoryId) {
        categoryService.deleteById(categoryId);
        logger.error("Deleted category {}", categoryId);
        return ResponseEntity.ok("Deleted category " + categoryId);
    }
    
}
