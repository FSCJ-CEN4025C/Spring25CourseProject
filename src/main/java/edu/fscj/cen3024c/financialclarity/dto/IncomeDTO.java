package edu.fscj.cen3024c.financialclarity.dto;

import java.time.LocalDateTime;

public class IncomeDTO {

    private Integer incomeId;
    private Integer userId;
    private Integer categoryId;
    private Float amount;
    private String name;
    private LocalDateTime createdAt; 
    private LocalDateTime updatedAt;

    public IncomeDTO(Integer incomeId, Integer userId, Integer categoryId, Float amount, String name, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.incomeId = incomeId;
        this.userId = userId;
        this.categoryId = categoryId;
        this.amount = amount;
        this.name = name;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Integer getIncomeId() {return this.incomeId;}
    public Integer getUserId() {return this.userId;}
    public Integer getCategoryId() {return this.categoryId;}
    public Float getAmount() {return this.amount;}
    public String getName() {return this.name;}
    public LocalDateTime getCreatedAt() {return this.createdAt;}
    public LocalDateTime getUpdateAt() {return this.updatedAt;}

    public void setIncomeId(Integer incomeId) {this.incomeId = incomeId;}
    public void setUserId(Integer userId) {this.userId = userId;}
    public void setCategoryId(Integer categoryId) {this.categoryId = categoryId;}
    public void setAmount(Float amount) {this.amount = amount;}
    public void setName(String name) {this.name = name;}
    public void setCreatedAt(LocalDateTime createdAt) {this.createdAt = createdAt;}
    public void setUpdatedAt(LocalDateTime updatedAt) {this.updatedAt = updatedAt;}

}
