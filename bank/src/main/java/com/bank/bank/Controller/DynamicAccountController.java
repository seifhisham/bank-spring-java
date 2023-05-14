package com.bank.bank.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bank.bank.Models.Account;
import com.bank.bank.Models.User;
import com.bank.bank.Repositories.AccountRepo;
import com.bank.bank.Repositories.AccountTypeRepo;
import com.bank.bank.Repositories.UserRepo;
import com.bank.bank.Services.AccountService;

@Controller
@RequestMapping("/thymeleaf")
public class DynamicAccountController {

    @Autowired
    private AccountRepo accountRepo;
    @Autowired
    private AccountTypeRepo accountTypeRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private AccountService accountService;

    @GetMapping("View-Account")
    public ModelAndView getAccountList() {
        ModelAndView mav = new ModelAndView("ViewAccount.html");
        List<Account> accountList = accountRepo.findAll();
        mav.addObject("accounts", accountList);
        return mav;
    }

    @GetMapping("add-account")
    public ModelAndView getAddAccountForm() {
        ModelAndView mav = new ModelAndView("AddAccount.html");
        Account account = new Account();

        mav.addObject("account", account);
        mav.addObject("accountTypes", accountTypeRepo.findAll());
        mav.addObject("users", userRepo.findAll());
        return mav;
    }

    // @GetMapping("add-account")
    // public ModelAndView getAddAccountForm(
    // @RequestParam(value = "accountType", required = false) Long Type_ID,
    // @RequestParam(value = "balance", required = false) double balance,
    // @AuthenticationPrincipal User user) {
    // ModelAndView mav = new ModelAndView("AddAccount.html");
    // Account account = new Account();

    // mav.addObject("account", account);
    // mav.addObject("accountTypes", accountTypeRepo.findAll());
    // mav.addObject("users", userRepo.findAll());
    // return mav;
    // }

    @PostMapping("/save-account")
    public String saveAccount(@RequestParam("accountType") Long typeId,
            @RequestParam("balance") double balance, @AuthenticationPrincipal User user) {

        accountService.saveAccount(typeId, balance, user.getId());
        return "redirect:/thymeleaf/View-Account";
    }

}
// @AuthenticationPrincipal User user