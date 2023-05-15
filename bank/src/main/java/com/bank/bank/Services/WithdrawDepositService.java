package com.bank.bank.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.bank.Models.Account;
import com.bank.bank.Models.Withdraw_Deposit;
import com.bank.bank.Repositories.AccountRepo;
import com.bank.bank.Repositories.WithdrawRepo;

@Service
public class WithdrawDepositService {

    @Autowired
    private AccountRepo accountRepository;

    @Autowired
    private WithdrawRepo withdrawRepo;

    public void SaveWithdraw(double amount, String date, Withdraw_Deposit.Type type, Long relatedAccountID) {
        Withdraw_Deposit withdraw = new Withdraw_Deposit();
        Account account = accountRepository.findById(relatedAccountID)
                .orElseThrow(() -> new IllegalArgumentException("Invalid account ID: " + relatedAccountID));

        withdraw.setRelatedAccount(account);
        withdraw.setAmount(amount);
        withdraw.setDate(date);
        withdraw.setType(type);
        withdrawRepo.save(withdraw);

        if (type == Withdraw_Deposit.Type.WITHDRAW) {
            account.setBalance(account.getBalance() - amount);
        } else if (type == Withdraw_Deposit.Type.DEPOSIT) {
            account.setBalance(account.getBalance() + amount);
        }

        accountRepository.save(account);
    }
}
