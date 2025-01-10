package edu.fscj.cen3024c.financialclarity.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "repayment plan")
public class RepaymentPlan {
    //Defining the columns in the database
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer planId;


    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false, unique = true)
    private Float totalExpense;

    @Column(nullable = false, unique = true)
    private Float payment;

    @Column(nullable = false, unique = true)
    private String timeLine;

    @Column(nullable = false, unique = true)
    private String category;

    @Column(nullable = false, unique = true)
    private String planName;


    // Getters and Setters
    //ExpenseId
    public Integer getPlanId() {
        return planId;
    }

    public void setPlanId(Integer planId) {
        this.planId = planId;
    }

    //User
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Float getTotalExpense() {
        return totalExpense;
    }

    public void setTotalExpense(Float totalExpense) {
        this.totalExpense = totalExpense;
    }
    //Amount
    public Float getPayment() {
        return payment;
    }

    public void setPayment(Float payment) {
        this.payment = payment;
    }

    //Category
    public String getTimeLine() {
        return timeLine;
    }

    public void setTimeLine(String timeLine) {
        this.timeLine= timeLine;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

}