package edu.fscj.cen3024c.financialclarity.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {
    //Defining the columns in the database
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false, unique = true, length = 255)
    private String username;

    @Column(nullable = false, length = 255, unique = true)
    private String email;

    @Column(nullable = false)
    private Integer age;

    @Column(nullable = false)
    private String totalIncome;

    @Column(nullable = false)
    private String totalExpenses;

    @Column(nullable = false)
    private String salt;

    @Column(nullable = false)
    private String hash;

    public User() {
        // Default constructor
    }

    public User(String username, Integer age, String email, String password, String totalIncome, String totalExpenses) {
        this.username = username;
        this.age = age;
        this.email = email;
        this.totalIncome = totalIncome;
        this.totalExpenses = totalExpenses;

    }

    public Integer getId() {return id;}
    public void setId(Integer id) {this.id = id;}

    public String getUsername() {return username;}
    public void setUsername(String username) {this.username = username;}

    public Integer getAge() {return age;}
    public void setAge(Integer age) {this.age = age;}

    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}

    public String getTotalIncome() {return totalIncome;}
    public void setTotalIncome(String totalIncome) {this.totalIncome = totalIncome;}

    public String getTotalExpenses() {return totalExpenses;}
    public void setTotalExpenses(String totalExpenses) {this.totalExpenses = totalExpenses;}

    public String getSalt() {return salt;}
    public void setSalt(String salt) {this.salt = salt;}

    public String getHash() {return hash;}
    public void setHash(String hash) {this.hash = hash;}


}