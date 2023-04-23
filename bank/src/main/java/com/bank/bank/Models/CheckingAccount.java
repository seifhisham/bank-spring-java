package com.bank.bank.Models;
import javax.persistence.*;

@Entity
@Table(name = "checking_accounts")
public class CheckingAccount extends Account {

    public CheckingAccount() {
        super();
        setAccountType(AccountType.CHECKING);
    }

    public CheckingAccount(User user, int balance, double interestRate, String email, String password) {
        super(user, AccountType.CHECKING, balance, interestRate, email, password);
    }
    
}
