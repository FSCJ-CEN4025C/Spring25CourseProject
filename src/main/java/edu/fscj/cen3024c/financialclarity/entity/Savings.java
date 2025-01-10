package edu.fscj.cen3024c.financialclarity.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "savings")
public class Savings {
    @Id
    @Column(name = "savingsId", nullable = false)
    private Integer savingsId;

    @Column(name = "savingsamount")
    private Integer savingsamount;


    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "description", length = Integer.MAX_VALUE)
    private String description;

    public Savings() {
        // This is the default constructor
    }

    public Savings(Integer savingsId, Integer savingsamount, User user, String description) {
        this.savingsId = savingsId;
        this.savingsamount = savingsamount;
        this.user = user;
        this.description = description;
    }

    public Integer getSavingsId() {
        return savingsId;
    }
    public void setSavingsId(Integer id) {
        this.savingsId = id;
    }

    public Integer getSavingsamount() {
        return savingsamount;
    }
    public void setSavingsamount(Integer savingsamount) {
        this.savingsamount = savingsamount;
    }

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

}