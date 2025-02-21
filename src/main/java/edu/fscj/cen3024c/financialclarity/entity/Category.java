package edu.fscj.cen3024c.financialclarity.entity;

import jakarta.persistence.*;
import edu.fscj.cen3024c.financialclarity.entity.CategoryType;

@Entity
@Table(name = "category")
public class Category {
    //Defining the columns in the database
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name="type", nullable = false)
    private CategoryType type;

    // Getters and Setters
    //ExpenseId
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    //UserID
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
        return "Category{" +
                "id=" + id +
                ", userId=" + user.getId() +
                ", name='" + name + '\'' +
                ", type=" + type +
                '}';
    }
}
