package edu.fscj.cen3024c.financialclarity.dto;

import java.time.LocalDateTime;

public class SavingsDTO {
    private Integer savingsId;
    private Integer savingsAmount;
    private Integer categoryId;
    private Integer userid;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public SavingsDTO(Integer savingsId, Integer savingsAmount, Integer categoryId, Integer userid, String description, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.savingsId = savingsId;
        this.savingsAmount = savingsAmount;
        this.categoryId = categoryId;
        this.userid = userid;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Integer getSavingsId() {return savingsId;}
    public void setSavingsId(Integer savingsId) {this.savingsId = savingsId;}

    public Integer getSavingsAmount() {return savingsAmount;}
    public void setSavingsAmount(Integer savingsAmount) {this.savingsAmount = savingsAmount;}

    public Integer getCategoryId() {return categoryId;}
    public void setCategoryId(Integer categoryId) {this.categoryId = categoryId;}

    public Integer getUserid() {return userid;}
    public void setUserid(Integer userid) {this.userid = userid;}

    public String getDescription() {return description;}
    public void setDescription(String description) {this.description = description;}

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {}

    public LocalDateTime getUpdatedAt() {return updatedAt;}
    public void setUpdatedAt(LocalDateTime updatedAt) {this.updatedAt = updatedAt;}
}
