package edu.fscj.cen3024c.financialclarity.dto;

import java.util.Date;

public class BudgetDTO {
    private Integer id;
    private Integer userId;
    private String budgetName;
    private Date timeCreated;

    public BudgetDTO(Integer id, Integer userId, String budgetName, Date timeCreated) {
        this.id = id;
        this.userId = userId;
        this.budgetName = budgetName;
        this.timeCreated = timeCreated;
    }

    public Integer getId() {return id;}
    public Integer getUserId() {return userId;}
    public String getBudgetName() {return budgetName;}
    public Date getTimeCreated() {return timeCreated;}

    public void setId(Integer id) {this.id = id;}
    public void setUserId(Integer userId) {this.userId = userId;}
    public void setBudgetName(String budgetName) {this.budgetName = budgetName;}
    public void setTimeCreated(Date timeCreated) {this.timeCreated = timeCreated;}

}
