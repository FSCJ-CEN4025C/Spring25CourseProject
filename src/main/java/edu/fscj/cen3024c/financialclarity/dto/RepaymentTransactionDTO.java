package edu.fscj.cen3024c.financialclarity.dto;

import edu.fscj.cen3024c.financialclarity.entity.Period;
import edu.fscj.cen3024c.financialclarity.entity.User;
import java.util.Date;

public class RepaymentTransactionDTO {

    private Integer id;
    private User user;
    private Integer planId;
    private Date date;
    private Double amount;

    public RepaymentTransactionDTO(Integer id,
                     User user,
                     Integer planId,
                     Date date,
                     Double amount) {
        this.id = id;
        this.user = user;
        this.planId = planId;
        this.date = date;
        this.amount = amount;
    }

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

    public void setDate(Date date) { this.date = date; }

    // Amount
    public Double getAmount() { return amount; }

    public void setAmount(Double amount) { this.amount = amount; }

    @Override
    public String toString() {
        return "RepaymentTransactionDTO{" +
                "id=" + id +
                ", userId=" + user.getId() +
                ", planId=" + planId +
                ", date=" + date +
                ", amount=" + amount +
                '}';
    }
}
