package com.bank.bank.Models;
import javax.persistence.*;


@Entity
@Table(name = "saving_accounts")
public class SavingAccount extends Account {

    public SavingAccount() {
        super();
        setAccountType(AccountType.SAVING);
    }

    public SavingAccount(User user, int balance, double interestRate, String email, String password) {
        super(user, AccountType.SAVING, balance, interestRate, email, password);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long getId() {
        return super.getId();
    }
    
}
    

