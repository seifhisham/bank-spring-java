package com.bank.bank.Models;

import javax.persistence.*;

@Entity
@Table(name = "accounts")
@Inheritance(strategy = InheritanceType.JOINED)

public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

   
    @Enumerated(EnumType.STRING)
    private AccountType accountType;
    public enum AccountType {
        SAVING,
        LOANING,
        CHECKING
    }
    


    private double balance;

    @Column(name = "interest_rate")
    private double interestRate;

  

    public Account() {}

    public Account(User user, AccountType accountType, int balance, double interestRate, String email, String password) {
        this.user = user;
        this.accountType = accountType;
        this.balance = balance;
        this.interestRate = interestRate;
       
    }

    public Long getId() {
        return this.id ;
    }

    // getters and setters
    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public AccountType getAccountType() {
        return this.accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public double getBalance() {
        return this.balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getInterestRate() {
        return this.interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

  
  
    }  



