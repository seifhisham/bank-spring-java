package com.bank.bank.Services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

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
     private WithdrawRepo  withdrawRepo;

    @Autowired
    private AccountRepo accountRepository;

    Account account=new Account();

    public void saveWithdrawDeposit(Long accountId, double amount, TransactionType transactionType) {
        Optional<Account> accountOptional = accountRepository.findById(accountId);
        if (!accountOptional.isPresent()) {
            throw new IllegalArgumentException("Invalid account id: " + accountId);
        }

        Account account = accountOptional.get();
        double oldBalance = account.getBalance();
        double newBalance;

        if (transactionType == TransactionType.WITHDRAW_DEPOSIT) {
            newBalance = oldBalance + amount;
        } else {
            newBalance = oldBalance - amount;
            if (newBalance < 0) {
                throw new IllegalArgumentException("Insufficient funds");
            }
        }

        account.setBalance(newBalance);
        accountRepository.save(account);

        Withdraw_Deposit withdrawDeposit = new Withdraw_Deposit();
        withdrawDeposit.setRelatedAccount(account);
        withdrawDeposit.setAmount(amount);
        withdrawDeposit.setTransactionType(transactionType);

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formatDateTime = now.format(formatter);

        withdrawDeposit.setDate(formatDateTime);

        withdrawRepo.save(withdrawDeposit);
    }
}
