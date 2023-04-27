package com.bank.bank.Controller;

import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.bank.Models.Account;
import com.bank.bank.Models.User;
import com.bank.bank.Repositories.AccountRepo;
import com.bank.bank.Services.LoggingService;

import net.bytebuddy.dynamic.DynamicType.Builder.FieldDefinition.Optional;

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
        account.setAccountType(Account.AccountType.valueOf(body.get("accountType")));
        account.setEmail(String.valueOf(body.get("email")));
        account.setInterestRate(Double.parseDouble(body.get("interestrate")));
        account.setPassword(String.valueOf(body.get("password")));
        User user=new User();
        user.setId(Integer.parseInt(body.get("userid")));
        this.accountRepo.save(account);
        return new ResponseEntity<>(account, HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deleteAccount(@PathVariable Long id) {
        java.util.Optional<Account> accountOptional = this.accountRepo.findById(id);
        if (accountOptional.isPresent()) {
            this.accountRepo.deleteById(id);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity updateaccount(@PathVariable long id, @RequestBody Map<String, String> body) {
        Account account = accountRepo.findById(id).orElse(null);
        if (account == null) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
        if (!body.containsKey("name")) {
           return new ResponseEntity<>("Name is required", HttpStatus.BAD_REQUEST);
        }
        account.setBalance(Double.parseDouble(body.get("balance")));
        account.setAccountType(Account.AccountType.valueOf(body.get("accountType")));
        account.setEmail(String.valueOf(body.get("email")));
        account.setInterestRate(Double.parseDouble(body.get("interestrate")));
        account.setPassword(String.valueOf(body.get("password")));
        User user=new User();
        user.setId(Integer.parseInt(body.get("userid")));
        this.accountRepo.save(account);
        return new ResponseEntity<>(account, HttpStatus.OK);
    }
    
    
   
}
   
