package com.bank.bank.Models;
import javax.persistence.*;

@Entity
@Table(name = "loaning_accounts")
public class LoaningAccount extends Account {
    public int loan_amount;
    public LoaningAccount() {
        super();
        setAccountType(AccountType.LOANING);
    }

    public LoaningAccount(User user, int balance, double interestRate, String email, String password) {
        super(user, AccountType.LOANING, balance, interestRate, email, password);
    }

    public int getLoan_amount() {
        return this.loan_amount;
    }

    public void setLoan_amount(int loan_amount) {
        this.loan_amount = loan_amount;
    }

}
