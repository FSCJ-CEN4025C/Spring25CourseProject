package edu.fscj.cen3024c.financialclarity.repository;

import edu.fscj.cen3024c.financialclarity.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    // Find all Users
    List<Category> findAll();
//    Category findByCategoryId(Integer categoryId);
    void deleteById(Integer Id);
    
}
