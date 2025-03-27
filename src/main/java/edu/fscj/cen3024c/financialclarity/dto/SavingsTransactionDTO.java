package edu.fscj.cen3024c.financialclarity.dto;

import java.time.LocalDateTime;

public class SavingsTransactionDTO {
    private Integer transactionId;
    private Integer savingsId;
    private Integer amount;
    private String transactionType; 
    private Integer userId;
    private String source; 
    private String status; 
    private String transactionMethod;
    private LocalDateTime transactionDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public SavingsTransactionDTO(Integer transactionId, Integer savingsId, Integer amount, String transactionType, Integer userId, String source, String status, String transactionMethod, LocalDateTime transactionDate, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.transactionId = transactionId;
        this.savingsId = savingsId;
        this.amount = amount;
        this.transactionType = transactionType;
        this.userId = userId;
        this.source = source;
        this.status = status;
        this.transactionMethod = transactionMethod;
        this.transactionDate = transactionDate;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Integer getTransactionId() { return transactionId; }
    public void setTransactionId(Integer transactionId) { this.transactionId = transactionId; }

    public Integer getSavingsId() { return savingsId; }
    public void setSavingsId(Integer savingsId) { this.savingsId = savingsId; }

    public Integer getAmount() { return amount; }
    public void setAmount(Integer amount) { this.amount = amount; }

    public String getTransactionType() { return transactionType; }
    public void setTransactionType(String transactionType) { this.transactionType = transactionType; }

    public Integer getUserId() { return userId; }
    public void setUserId(Integer userId) { this.userId = userId; }

    public String getSource() { return source; }
    public void setSource(String source) { this.source = source; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getTransactionMethod() { return transactionMethod; }
    public void setTransactionMethod(String transactionMethod) { this.transactionMethod = transactionMethod; }

    public LocalDateTime getTransactionDate() { return transactionDate; }
    public void setTransactionDate(LocalDateTime transactionDate) { this.transactionDate = transactionDate; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
