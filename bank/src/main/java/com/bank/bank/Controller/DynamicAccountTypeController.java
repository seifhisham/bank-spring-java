package com.bank.bank.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bank.bank.Models.AccountType;
import com.bank.bank.Models.User;
import com.bank.bank.Repositories.AccountTypeRepo;

@Controller
@RequestMapping("/thymeleaf")
public class DynamicAccountTypeController {
    
    @Autowired
    private AccountTypeRepo accountTypeRepo;

    @GetMapping("View-accounttype")
    public ModelAndView getPost() {
        ModelAndView mav = new ModelAndView("AccountType.html");

        List<AccountType> accountList = accountTypeRepo.findAll();
        mav.addObject("users", accountList);
        return mav;
    }

}
