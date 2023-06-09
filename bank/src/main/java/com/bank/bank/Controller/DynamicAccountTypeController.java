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
import com.bank.bank.Models.AccountType;
import com.bank.bank.Repositories.AccountTypeRepo;

@Controller
@RequestMapping("/thymeleaf")
public class DynamicAccountTypeController {

    @Autowired
    private AccountTypeRepo accountTypeRepo;

    @GetMapping("View-Account-Type")
    public ModelAndView getAccountList() {
        ModelAndView mav = new ModelAndView("AccountType.html");

        List<AccountType> accountList = accountTypeRepo.findAll();
        mav.addObject("accountTypes", accountList);
        return mav;
    }

    @GetMapping("Add-Account-Type")
    public ModelAndView getAddPostForm() {
        ModelAndView mav = new ModelAndView("AddType.html");
        AccountType accountType = new AccountType();
        mav.addObject("accountTypes", accountType);
        return mav;
    }

    @PostMapping("Save-Account-Type")
    public String savePost(@ModelAttribute AccountType accountType) {

        this.accountTypeRepo.save(accountType);

        return "redirect:/thymeleaf/View-Account-Type";

    }

    @GetMapping("Delete-Account-Type")
    public String deletePost(@RequestParam("Id") Long Id) {
        this.accountTypeRepo.deleteById(Id);
        return "redirect:/thymeleaf/View-Account-Type";
    }

    @GetMapping("Update-Account-Type")
    public ModelAndView getUpdatePostForm(@RequestParam("Id") Long Id) {
        ModelAndView mav = new ModelAndView("AddType.html");
        AccountType accountType = this.accountTypeRepo.findById(Id).orElse(null);
        mav.addObject("accountTypes", accountType);
        return mav;
    }

}
