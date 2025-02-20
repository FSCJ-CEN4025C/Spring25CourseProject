package edu.fscj.cen3024c.financialclarity.dto;

import edu.fscj.cen3024c.financialclarity.entity.CategoryType;

public class CategoryDTO {
    private Integer id;
    private Integer userId;
    private String name;
    private CategoryType type;

    public CategoryDTO(Integer id, Integer userId, String name, CategoryType type) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.type = type;
    }
    
    // Getters and Setters  
    //id
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    //userId
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    //Name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //Type
    public CategoryType getType() {
        return type;
    }
    
    public void setType(CategoryType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "CategoryDTO{" +
                "id=" + id +
                ", userId=" + userId +
                ", name='" + name + '\'' +
                ", type=" + type +
                '}';
    }

}
