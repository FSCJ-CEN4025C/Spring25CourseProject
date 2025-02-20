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
    private Integer userid;

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
    public Integer getUserID() {
        return userid;
    }

    public void setUserID(Integer userid) {
        this.userid = userid;
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
    
}
