package com.bank.bank.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.bank.Models.Account;
import com.bank.bank.Models.Withdraw_Deposit;
import com.bank.bank.Models.Transaction.TransactionType;
import com.bank.bank.Repositories.AccountRepo;
import com.bank.bank.Repositories.WithdrawRepo;

@Service
public class WithdrawDepositService {
    @Autowired
    private AccountRepo accountRepository;

    @Autowired
    private WithdrawRepo withdrawRepo;

    public void SaveWithdraw(double amount, String date, TransactionType transactionType, Long RelatedAccount_ID) {
        Withdraw_Deposit withdraw = new Withdraw_Deposit();
        Account account = accountRepository.findById(RelatedAccount_ID)
                .orElseThrow(() -> new IllegalArgumentException("Invalid account  id: " + RelatedAccount_ID));

        withdraw.setRelatedAccount(account);
        withdraw.setAmount(amount);
        withdraw.setDate(date);
        withdraw.setTransactionType(transactionType);
        withdrawRepo.save(withdraw);

        account.setBalance(account.getBalance() - amount);
        accountRepository.save(account);

    }

}
