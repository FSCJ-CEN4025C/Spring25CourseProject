package edu.fscj.cen3024c.financialclarity.dto;

public class RepaymentPlanDTO {
    private Integer planId;
    private Integer userId;
    private Float totalExpense;
    private Float payment;
    private String timeLine;
    private String category;
    private String planName;

    public RepaymentPlanDTO(Integer planId, Integer userId, Float totalExpense, Float payment, String timeLine, String category, String planName) {
        this.planId = planId;
        this.userId = userId;
        this.totalExpense = totalExpense;
        this.payment = payment;
        this.timeLine = timeLine;
        this.category = category;
        this.planName = planName;
    }

    public Integer getPlanId() {
        return planId;
    }

    public void setPlanId(Integer id) {
        this.planId = planId;
    }

    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public Float getTotalExpense() {
        return totalExpense;
    }
    public void setTotalExpense(Float totalExpense) {
        this.totalExpense = totalExpense;
    }
    public Float getPayment() {
        return payment;
    }
    public void setPayment(Float payment) {
        this.payment = payment;
    }
    public String getTimeLine() {return timeLine;}
    public void setTimeLine(String timeLine) {
        this.timeLine = timeLine;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public String getName() {return planName;}
    public void setName(String planName) {this.planName = planName;}
    }
