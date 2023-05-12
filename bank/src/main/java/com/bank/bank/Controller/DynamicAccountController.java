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
import com.bank.bank.Repositories.AccountRepo;

@Controller
@RequestMapping("/thymeleaf")
public class DynamicAccountController {

    @Autowired
    private AccountRepo accountRepo;

  
    @GetMapping("View-Account")
    public ModelAndView getAccountList() {
    ModelAndView mav = new ModelAndView("AddAccount.html");
    List<Account> accountList = accountRepo.findAll();
    mav.addObject("accounts", accountList);
    return mav;
    }

    @GetMapping("add-account")
    public ModelAndView getAddPostForm() {
        ModelAndView mav = new ModelAndView("Addaccount.html");
        Account account = new Account();
        mav.addObject("account", account);
        return mav;
    }

    @PostMapping("save-accounttype")
    public String savePost(@ModelAttribute Account account) {

        this.accountRepo.save(account);

        return "redirect:/thymeleaf/View-account";

    }

    @GetMapping("delete-account")
    public String deletePost(@RequestParam("Id") Long Id) {
        this.accountRepo.deleteById(Id);
        return "redirect:/thymeleaf/View-account";
    }

    @GetMapping("update-account")
    public ModelAndView getUpdatePostForm(@RequestParam("Id") Long Id) {
        ModelAndView mav = new ModelAndView("Addaccount.html");
        Account account = this.accountRepo.findById(Id).orElse(null);
        mav.addObject("account", account);
        return mav;
    }
}
