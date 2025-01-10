package edu.fscj.cen3024c.financialclarity.dto;

public class SavingsDTO {
    private Integer savingsId;
    private Integer savingsAmount;
    private Integer userid;
    private String description;

    public SavingsDTO(Integer savingsId, Integer savingsAmount, Integer userid, String description) {
        this.savingsId = savingsId;
        this.savingsAmount = savingsAmount;
        this.userid = userid;
        this.description = description;
    }

    public Integer getSavingsId() {return savingsId;}
    public void setSavingsId(Integer savingsId) {this.savingsId = savingsId;}

    public Integer getSavingsAmount() {return savingsAmount;}
    public void setSavingsAmount(Integer savingsAmount) {this.savingsAmount = savingsAmount;}

    public Integer getUserid() {return userid;}
    public void setUserid(Integer userid) {this.userid = userid;}

    public String getDescription() {return description;}
    public void setDescription(String description) {this.description = description;}


}
