package com.bank.bank.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Account {

    public static final String AccountType = null;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String accountType;
    private double balance;

    public Account() {
    }


    public Account(Long id, String accountType, double balance) {
        this.id = id;
        this.accountType = accountType;
        this.balance = balance;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccountType() {
        return this.accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public double getBalance() {
        return this.balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
    
}
