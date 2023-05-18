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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

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
    
        // Get the logged-in user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
    
        // Retrieve the list of accounts for the logged-in user
        List<Account> accountList = accountRepo.findAllByUser(user);
    
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

    @PostMapping("/save-account")
    public String saveAccount(@RequestParam("accountType") Long typeId,
            @RequestParam("balance") double balance, @RequestParam("userId") String userId) {

        accountService.saveAccount(typeId, balance, userId);
        return "redirect:/thymeleaf/View-Account";
    }

    @GetMapping("delete-account")
    public String deletePost(@RequestParam("Id") Long Id) {
        this.accountRepo.deleteById(Id);
        return "redirect:/thymeleaf/View-Account";
    }

    @GetMapping("update-account")
    public ModelAndView getUpdatePostForm(@RequestParam("Id") Long Id) {
        ModelAndView mav = new ModelAndView("Addaccount.html");

        mav.addObject("accountTypes", accountTypeRepo.findAll());
        mav.addObject("users", userRepo.findAll());
        Account account = this.accountRepo.findById(Id).orElse(null);

        mav.addObject("accounts", account);
        return mav;
    }

}
