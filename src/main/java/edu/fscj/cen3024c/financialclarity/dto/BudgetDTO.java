package edu.fscj.cen3024c.financialclarity.dto;

import edu.fscj.cen3024c.financialclarity.entity.Period;
import edu.fscj.cen3024c.financialclarity.entity.User;
import java.util.Date;

public class BudgetDTO {
    private Integer id;
    private User user;
    private Integer categoryId;
    private Double amount;
    private Period period;
    private Date startDate;
    private Date endDate;

    public BudgetDTO(Integer id, 
                     User user, 
                     Integer categoryId, 
                     Double amount, 
                     Period period, 
                     Date startDate, 
                     Date endDate) {
        this.id = id;
        this.user = user;
        this.categoryId = categoryId;
        this.amount = amount;
        this.period = period;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    // Getters and Setters
    //Id
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

    public void setUserId(User user) {
        this.user = user;
    }

    //CategoryID
    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    //Amount
    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    //Period
    public Period getPeriod() {
        return period;
    }

    public void setPeriod(Period period) {
        this.period = period;
    }

    //StartDate
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    //EndDate
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "BudgetDTO{" +
                "id=" + id +
                ", userId=" + user.getId() +
                ", categoryId=" + categoryId +
                ", amount=" + amount +
                ", period=" + period +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }

}
