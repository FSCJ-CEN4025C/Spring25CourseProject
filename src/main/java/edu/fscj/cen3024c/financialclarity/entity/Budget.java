package edu.fscj.cen3024c.financialclarity.entity;

import jakarta.persistence.*;
import edu.fscj.cen3024c.financialclarity.entity.Period;

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
    private Integer userid;

    @Column(name = "category_id", nullable = true)
    private Integer categoryid;

    @Column(nullable = false)
    private Double amount;

    @Enumerated(EnumType.STRING)
    @Column(name="period", nullable = false)
    private Period period;

    @Column(name = "start_date", nullable = false)
    private Date startDate;

    @Column(name = "end_date", nullable = false)
    private Date endDate;

    // @Column(nullable = false, unique = true)
    // private String budgetName;

    // @Column(nullable = false)
    // private Date timeCreated;


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

    //CategoryID
    public Integer getCategoryID() {
        return categoryid;
    }

    public void setCategoryID(Integer categoryid) {
        this.categoryid = categoryid;
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
    

    //Amount
    // public String getBudgetName() {
    //     return budgetName;
    // }

    // public void setBudgetName(String budgetName) {
    //     this.budgetName = budgetName;
    // }

    //Category
    // public Date getTimeCreate() {
    //     return timeCreated;
    // }

    // public void setTimeCreate(Date timeCreate) {
    //     this.timeCreated = timeCreate;
    // }
}