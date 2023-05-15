package com.bank.bank.Models;

import javax.persistence.*;



@Entity
public class Withdraw_Deposit extends Transaction {

    @Enumerated(EnumType.STRING)
    private Type type;
    public enum Type {
        WITHDRAW,
        DEPOSIT
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account relatedAccount;

    public Withdraw_Deposit() {
    }

    public Withdraw_Deposit(Type type, Account relatedAccount) {
    this.type = type;
    this.relatedAccount = relatedAccount;
}

    public Withdraw_Deposit(Long id, double amount, String date, TransactionType transactionType, Type type,
        Account relatedAccount) {
    super(id, amount, date, transactionType);
    this.type = type;
    this.relatedAccount = relatedAccount;
}

    public Type getType() {
        return this.type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Account getRelatedAccount() {
        return this.relatedAccount;
    }

    public void setRelatedAccount(Account relatedAccount) {
        this.relatedAccount = relatedAccount;
    }
}
