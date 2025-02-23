package edu.fscj.cen3024c.financialclarity.dto;

import java.time.LocalDateTime;

public class ExpensesDTO {
    private Integer expenseId;
    private Integer userId;
    private Integer categoryId;
    private Float amount;
    private String name;
    private LocalDateTime createdAt; 
    private LocalDateTime updatedAt;

    public ExpensesDTO(Integer expenseId, Integer userId, Integer categoryId, Float amount, String name, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.expenseId = expenseId;
        this.userId = userId;
        this.categoryId = categoryId;
        this.amount = amount;
        this.name = name;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;

    }

    public Integer getExpenseId() {return expenseId;}
    public void setExpenseId(Integer expenseId) {this.expenseId = expenseId;}

    public Integer getUserId() {return userId;}
    public void setUserId(Integer userId) {this.userId = userId;}

    public Integer getCategoryId() {return this.categoryId;}
    public void setCategoryId(Integer categoryId) {this.categoryId = categoryId;}

    public Float getAmount() {return amount;}
    public void setAmount(Float amount) {this.amount = amount;}

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public LocalDateTime getCreatedAt() {return this.createdAt;}
    public void setCreatedAt(LocalDateTime createdAt) {this.createdAt = createdAt;}

    public LocalDateTime getUpdateAt() {return this.updatedAt;}
    public void setUpdatedAt(LocalDateTime updatedAt) {this.updatedAt = updatedAt;}
}


