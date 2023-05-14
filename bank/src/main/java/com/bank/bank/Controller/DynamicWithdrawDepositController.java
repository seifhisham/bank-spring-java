package com.bank.bank.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bank.bank.Models.Account;
import com.bank.bank.Models.Withdraw_Deposit;
import com.bank.bank.Repositories.WithdrawRepo;

@Controller
@RequestMapping("/thymeleaf")

public class DynamicWithdrawDepositController {

    @Autowired
    private WithdrawRepo withdrawRepo;

    @GetMapping("view-withdraw")
    public ModelAndView getListOfTransfers() {
        ModelAndView mav = new ModelAndView("WithdrawDeposit.html");
        List<Withdraw_Deposit> ListOfTransfers = withdrawRepo.findAll();
        mav.addObject("transactions", ListOfTransfers);
        return mav;
    }

}
