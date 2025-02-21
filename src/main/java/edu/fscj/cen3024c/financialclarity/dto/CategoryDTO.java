package edu.fscj.cen3024c.financialclarity.dto;

import edu.fscj.cen3024c.financialclarity.entity.CategoryType;
import edu.fscj.cen3024c.financialclarity.entity.User;

public class CategoryDTO {
    private Integer id;
    private User user;
    private String name;
    private CategoryType type;

    public CategoryDTO(Integer id, User user, String name, CategoryType type) {
        this.id = id;
        this.user = user;
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
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
                ", userId=" + user.getId() +
                ", name='" + name + '\'' +
                ", type=" + type +
                '}';
    }

}
