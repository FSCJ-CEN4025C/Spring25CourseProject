package edu.fscj.cen3024c.financialclarity.entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "repayment_transaction")
public class RepaymentTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false, unique = true)
    private Integer planId;

    @Column(nullable = false, unique = true)
    private Date date;

    @Column(nullable = false, unique = true)
    private Double amount;

    // Getters and Setters
    // Id
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    // User
    public User getUser() { return user; }

    public void setUser(User user) { this.user = user; }

    // Plan Id
    public Integer getPlanId() {
        return planId;
    }

    public void setPlanId(Integer id) {
        this.planId = id;
    }

    // Date
    public Date getDate() { return date; }

    public void setDate(Date date) { this.date = this.date; }

    // Amount
    public Double getAmount() { return amount; }

    public void setAmount(Double amount) { this.amount = amount; }
}
