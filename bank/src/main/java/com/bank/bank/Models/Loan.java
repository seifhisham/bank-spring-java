package com.bank.bank.Models;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Account relatedAccount;

    private double amount;

    @Enumerated(EnumType.STRING)
    private LoanStatus status;

    // Add getters/setters and constructors

    public enum LoanStatus {
        APPROVED,
        DECLINED,
        WAITING
    }

    public Loan() {
    }

    public Loan(Long id, Account relatedAccount, double amount, LoanStatus status) {
        this.id = id;
        this.relatedAccount = relatedAccount;
        this.amount = amount;
        this.status = status;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Account getRelatedAccount() {
        return this.relatedAccount;
    }

    public void setRelatedAccount(Account relatedAccount) {
        this.relatedAccount = relatedAccount;
    }

    public double getAmount() {
        return this.amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LoanStatus getStatus() {
        return this.status;
    }

    public void setStatus(LoanStatus status) {
        this.status = status;
    }

    public Loan id(Long id) {
        setId(id);
        return this;
    }

    public Loan relatedAccount(Account relatedAccount) {
        setRelatedAccount(relatedAccount);
        return this;
    }

    public Loan amount(double amount) {
        setAmount(amount);
        return this;
    }

    public Loan status(LoanStatus status) {
        setStatus(status);
        return this;
    }

}
