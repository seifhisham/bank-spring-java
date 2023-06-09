package com.bank.bank.Models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class AccountType {

    @OneToMany(mappedBy = "accounttype", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Account> accounttype = new ArrayList<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String title;

    @Column(name = "interest_rate")
    private double interestRate;

    public AccountType() {
    }

    public AccountType(Long id, String title, double interestRate) {
        this.id = id;
        this.title = title;
        this.interestRate = interestRate;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getInterestRate() {
        return this.interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public AccountType id(Long id) {
        setId(id);
        return this;
    }

    public AccountType title(String title) {
        setTitle(title);
        return this;
    }

    public AccountType interestRate(double interestRate) {
        setInterestRate(interestRate);
        return this;
    }

}


