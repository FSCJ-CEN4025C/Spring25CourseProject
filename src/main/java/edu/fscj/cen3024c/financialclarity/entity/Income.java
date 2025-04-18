package edu.fscj.cen3024c.financialclarity.entity;

import java.time.LocalDateTime;

import org.springframework.cglib.core.Local;

import jakarta.persistence.*;

@Entity
@Table(name = "income")
public class Income {
    //Defining the columns in the database
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer incomeId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @Column(nullable = false)
    private Float amount;

    @Column(nullable = false)
    private String name;

    @Column()
    private LocalDateTime createdAt;
    
    @Column()
    private LocalDateTime updatedAt;


    public Integer getIncomeId() {return incomeId;}
    public void setIncomeId(Integer incomeId) {this.incomeId = incomeId;}

    public User getUser() {return user;}
    public void setUser(User user) {this.user = user;}

    public Category getCategory() {return category;}
    public void setCategory(Category category) {this.category = category;} 

    public Float getAmount() {return amount;}
    public void setAmount(Float amount) {this.amount = amount;}
  
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}



    public LocalDateTime getCreatedAt() {return createdAt;}
    public void setCreatedAt(LocalDateTime date) {this.createdAt = date;}

    public LocalDateTime getUpdatedAt() {return updatedAt;}


    // This annotation runs the function right before an new item is inserted into the database
    @PrePersist
    void prePersist() {
      if (this.createdAt == null) {
        this.createdAt = LocalDateTime.now();
      }
      this.updatedAt = LocalDateTime.now();
    }
  
    // This annotation runs the funciton right before an item is updated in the datebase
    @PreUpdate
    void preUpdate() {
      this.updatedAt = LocalDateTime.now();
    }


}