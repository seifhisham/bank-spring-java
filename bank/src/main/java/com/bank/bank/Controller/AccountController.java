package com.bank.bank.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.bank.Models.Account;
import com.bank.bank.Repositories.AccountRepo;
import com.bank.bank.Services.LoggingService;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private LoggingService loggingService;
    @Autowired
    private AccountRepo accountRepo;

    @GetMapping("")
    public ResponseEntity GetAccount() {
        loggingService.log("User fetched all accounts");
        List<Account> accounts = this.accountRepo.findAll();
        return new ResponseEntity(accounts, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Account> createAccount(@RequestBody Map<String, String> body) {
        Account account = new Account();
        // Use "balance" and "accountType" as keys to get the values from the request
        // body
        account.setBalance(Double.parseDouble(body.get("balance")));
        account.setAccountType(String.valueOf(body.get("accountType")));
        this.accountRepo.save(account);
        return new ResponseEntity<>(account, HttpStatus.CREATED);
    }
}