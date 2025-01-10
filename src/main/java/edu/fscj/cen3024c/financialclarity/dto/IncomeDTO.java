package edu.fscj.cen3024c.financialclarity.dto;


public class IncomeDTO {

    private Integer incomeId;
    private Integer userId;
    private Float amount;
    private String name;

    public IncomeDTO(Integer incomeId, Integer userId, Float amount, String name) {
        this.incomeId = incomeId;
        this.userId = userId;
        this.amount = amount;
        this.name = name;
    }

    public Integer getIncomeId() {return this.incomeId;}
    public Integer getUserId() {return this.userId;}
    public Float getAmount() {return this.amount;}
    public String getName() {return this.name;}

    public void setIncomeId(Integer incomeId) {this.incomeId = incomeId;}
    public void setUserId(Integer userId) {this.userId = userId;}
    public void setAmount(Float amount) {this.amount = amount;}
    public void setName(String name) {this.name = name;}

}
