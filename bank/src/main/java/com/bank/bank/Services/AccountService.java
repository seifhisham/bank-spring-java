package com.bank.bank.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.bank.Models.Account;
import com.bank.bank.Models.AccountType;
import com.bank.bank.Models.User;
import com.bank.bank.Repositories.AccountRepo;
import com.bank.bank.Repositories.AccountTypeRepo;
import com.bank.bank.Repositories.UserRepo;

@Service
public class AccountService {

    @Autowired
    private AccountRepo accountRepository;

    @Autowired
    private AccountTypeRepo accountTypeRepository;
    @Autowired
    private UserRepo userRepo;

    public void saveAccount(Long typeId, double balance, String userId) {
        Account account = new Account();
        User user = new User();
        user = userRepo.findById(userId).orElse(null);
        account.setUser(user);

        AccountType accountType = accountTypeRepository.findById(typeId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid account type id: " + typeId));
        account.setAccounttype(accountType);

        account.setBalance(balance);

        accountRepository.save(account);
    }

}
