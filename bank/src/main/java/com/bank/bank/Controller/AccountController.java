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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

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
public ResponseEntity<Account> updateAccount(@PathVariable @NotNull Long id,
                                               @RequestBody @NotNull Map<String, String> body,
                                               @RequestParam(name = "name", required = true) @NotBlank String name) {
    Account account = accountRepo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    account.setBalance(Double.parseDouble(body.getOrDefault("balance", "0.0")));
    account.setAccountType(Account.AccountType.valueOf(body.getOrDefault("accountType", "CHECKING")));
    account.setEmail(String.valueOf(body.get("email")));
    account.setInterestRate(Double.parseDouble(body.getOrDefault("interestrate", "0.0")));
    account.setPassword(String.valueOf(body.get("password")));
    User user = new User();
    user.setId(Integer.parseInt(body.get("userid")));
    //account.setName(name);
    this.accountRepo.save(account);
    return ResponseEntity.ok(account);
}

    
   
}
   
