package com.bank.bank.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
    ModelAndView mav = new ModelAndView("AddAcount.html");
    List<Account> accountList = accountRepo.findAll();
    mav.addObject("accounts", accountList);
    return mav;
}
}
