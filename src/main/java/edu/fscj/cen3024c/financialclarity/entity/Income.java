package edu.fscj.cen3024c.financialclarity.entity;

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

    @Column(nullable = false, unique = true)
    private Float amount;

    @Column(nullable = false, unique = true)
    private String name;



    // Getters and Setters
    //IncomeId
    public Integer getIncomeId() {return incomeId;}
    public void setIncomeId(Integer incomeId) {this.incomeId = incomeId;}
    //UserID
    public User getUser() {return user;}
    public void setUser(User user) {this.user = user;}
    //Amount
    public Float getAmount() {return amount;}
    public void setAmount(Float amount) {this.amount = amount;}
    //Name
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}


}