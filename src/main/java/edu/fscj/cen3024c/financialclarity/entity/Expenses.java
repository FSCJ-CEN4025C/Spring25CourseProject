package edu.fscj.cen3024c.financialclarity.entity;

import java.time.LocalDateTime; 

import jakarta.persistence.*;

@Entity
@Table(name = "expenses")
public class Expenses {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer expenseId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @Column(nullable = false, unique = true)
    private Float amount;

    @Column(nullable = false, unique = true)
    private String name;

    @Column()
    private LocalDateTime createdAt;
    
    @Column()
    private LocalDateTime updatedAt;


    public Integer getExpenseId() {return expenseId;}
    public void setExpenseId(Integer expenseId) {this.expenseId = expenseId;}

    public User getUser() {return user;}
    public void setUser(User user) {this.user = user;}

    public Category getCategory() {return category;}
    public void setCategory(Category category) {this.category = category;} 

    public Float getAmount() {return amount;}
    public void setAmount(Float amount) {this.amount = amount;}

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public LocalDateTime getCreatedAt() {return createdAt;}

    public LocalDateTime getUpdatedAt() {return updatedAt;}


    // This annotation runs the function right before an new item is inserted into the database
    @PrePersist
    void prePersist() {
      this.createdAt = this.updatedAt = LocalDateTime.now();
    }
    
    // This annotation runs the funciton right before an item is updated in the datebase
    @PreUpdate
    void preUpdate() {
      this.updatedAt = LocalDateTime.now();
    }
}