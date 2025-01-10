package edu.fscj.cen3024c.financialclarity.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "budget")
public class Budget {
    //Defining the columns in the database
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false, unique = true)
    private String budgetName;

    @Column(nullable = false)
    private Date timeCreated;


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

    //Amount
    public String getBudgetName() {
        return budgetName;
    }

    public void setBudgetName(String budgetName) {
        this.budgetName = budgetName;
    }

    //Category
    public Date getTimeCreate() {
        return timeCreated;
    }

    public void setTimeCreate(Date timeCreate) {
        this.timeCreated = timeCreate;
    }
}