package com.bank.bank.Models;

import javax.persistence.*;

@Entity
public class Withdraw_Deposit extends transaction {
    private String relatedAccount;

    public Withdraw_Deposit() {
    }

    public Withdraw_Deposit(String relatedAccount) {
        this.relatedAccount = relatedAccount;
    }

    public String getRelatedAccount() {
        return this.relatedAccount;
    }

    public void setRelatedAccount(String relatedAccount) {
        this.relatedAccount = relatedAccount;
    }

}