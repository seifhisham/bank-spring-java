package com.bank.bank.Models;

import javax.persistence.*;

@Entity
public class Withdraw_Deposit extends Transaction {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account relatedAccount;

    public Withdraw_Deposit() {
    }

    public Account getRelatedAccount() {
        return this.relatedAccount;
    }

    public void setRelatedAccount(Account relatedAccount) {
        this.relatedAccount = relatedAccount;
    }

}
