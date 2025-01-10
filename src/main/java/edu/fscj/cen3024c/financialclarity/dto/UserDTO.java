package edu.fscj.cen3024c.financialclarity.dto;

public class UserDTO {
    private Integer id;
    private String username;
    private String password;
    private String email;
    private Integer age;
    private String totalIncome;
    private String totalExpense;


    public UserDTO(){}

    public UserDTO(Integer id, String username, String email, Integer age, String totalIncome, String totalExpense) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.age = age;
        this.totalIncome = totalIncome;
        this.totalExpense = totalExpense;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public Integer getAge() {
        return age;
    }
    public void setAge(Integer age) {
        this.age = age;
    }
    public String getTotalIncome() {
        return totalIncome;
    }
    public void setTotalIncome(String totalIncome) {
        this.totalIncome = totalIncome;
    }
    public String getTotalExpense() {
        return totalExpense;
    }
    public void setTotalExpense(String totalExpense) {
        this.totalExpense = totalExpense;
    }


}
