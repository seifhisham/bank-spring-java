package com.bank.bank.Models;

import javax.persistence.*;
import java.util.Objects;

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

  

   @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Type_id")
    private AccountType accounttype;

    private double balance;


    public Account() {
    }

    public Account(Long id, User user, AccountType accounttype, double balance) {
        this.id = id;
        this.user = user;
        this.accounttype = accounttype;
        this.balance = balance;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public AccountType getAccounttype() {
        return this.accounttype;
    }

    public void setAccounttype(AccountType accounttype) {
        this.accounttype = accounttype;
    }

    public double getBalance() {
        return this.balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Account id(Long id) {
        setId(id);
        return this;
    }

    public Account user(User user) {
        setUser(user);
        return this;
    }

    public Account accounttype(AccountType accounttype) {
        setAccounttype(accounttype);
        return this;
    }

    public Account balance(double balance) {
        setBalance(balance);
        return this;
    }




  
  
    }  



