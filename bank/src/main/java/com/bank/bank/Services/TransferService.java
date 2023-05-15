package com.bank.bank.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.bank.Models.Account;
import com.bank.bank.Models.Transfers;
import com.bank.bank.Models.Transaction.TransactionType;
import com.bank.bank.Models.Withdraw_Deposit;
import com.bank.bank.Repositories.AccountRepo;
import com.bank.bank.Repositories.TransfersRepo;

@Service
public class TransferService {
    @Autowired
    private AccountRepo accountRepository;

    @Autowired
    private TransfersRepo transfersRepo;

    public void SaveWithdraw(double amount, String date, Long SenderId, Long ReceiverId) {
        Account sender = accountRepository.findById(SenderId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid account  id: " + SenderId));

        Account receiver = accountRepository.findById(ReceiverId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid account  id: " + ReceiverId));

        Transfers transfers = new Transfers();

        transfers.setSourceAccount(sender);
        transfers.setDestinationAccount(receiver);
        transfers.setAmount(amount);
        transfers.setDate(date);

        transfersRepo.save(transfers);

        sender.setBalance(sender.getBalance() - amount);
        accountRepository.save(sender);

        receiver.setBalance(receiver.getBalance() + amount);
        accountRepository.save(receiver);

    }
}
