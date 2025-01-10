package edu.fscj.cen3024c.financialclarity.dto;

import jakarta.persistence.Column;

public class ExpensesDTO {
    private Integer expenseId;
    private Integer userId;
    private Float amount;
    private String name;

    public ExpensesDTO(Integer expenseId, Integer userId, Float amount, String name) {
        this.expenseId = expenseId;
        this.userId = userId;
        this.amount = amount;
        this.name = name;

    }

    public Integer getExpenseId() {
        return expenseId;
    }

    public void setExpenseId(Integer expenseId) {
        this.expenseId = expenseId;
    }

    //UserID
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    //Amount
    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    //Category
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}


