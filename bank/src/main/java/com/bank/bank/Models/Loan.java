package com.bank.bank.Models;


import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // @ManyToOne
    private Long relatedAccount;

    private double amount;

    @Enumerated(EnumType.STRING)
    private LoanStatus status;



    public enum LoanStatus {
        APPROVED,
        DECLINED,
        WAITING
    }

    public Loan() {
    }

    public Loan(Long id, Long relatedAccount, double amount, LoanStatus status) {
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

    public Long getRelatedAccount() {
        if (relatedAccount != null) {
            return relatedAccount;
        } else {
            return 0L; // Or any other default value that makes sense in your application
        }
    }

    public void setRelatedAccount(long relatedAccount) {
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

    public Loan relatedAccount(long relatedAccount) {
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

    public void save(Loan loan) {
    }

    public List<Loan> findAll() {
        return null;
    }

}
