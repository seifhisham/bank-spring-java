package com.bank.bank.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bank.bank.Models.Account;
import com.bank.bank.Models.AccountType;
import com.bank.bank.Repositories.AccountRepo;
import com.bank.bank.Repositories.AccountTypeRepo;
import com.bank.bank.Repositories.UserRepo;

@Controller
@RequestMapping("/thymeleaf")
public class DynamicAccountController {

    @Autowired
    private AccountRepo accountRepo;
    @Autowired
    private AccountTypeRepo accountTypeRepo;
    @Autowired
    private UserRepo userRepo;

    @GetMapping("View-Account")
    public ModelAndView getAccountList() {
        ModelAndView mav = new ModelAndView("AddAccount.html");
        List<Account> accountList = accountRepo.findAll();
        mav.addObject("account", accountList);
        return mav;
    }

    @GetMapping("add-account")
    public ModelAndView getAddAccountForm() {
        ModelAndView mav = new ModelAndView("AddType.html");
        Account account = new Account();

        mav.addObject("account", account);
        mav.addObject("accountTypes", accountTypeRepo.findAll());
        mav.addObject("users", userRepo.findAll());
        return mav;
    }

    @PostMapping("save-account")
    public String saveAccount(@ModelAttribute Account account) {
        this.accountRepo.save(account);
        return "redirect:/thymeleaf/View-Account";
    }

}
