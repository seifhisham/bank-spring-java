package com.bank.bank.Controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import java.time.LocalDate;

import com.bank.bank.Models.User;
import com.bank.bank.Models.Withdraw_Deposit;
import com.bank.bank.Models.Transaction.TransactionType;
import com.bank.bank.Repositories.AccountRepo;
import com.bank.bank.Repositories.WithdrawRepo;
import com.bank.bank.Services.WithdrawDepositService;

@Controller
@RequestMapping("/thymeleaf")
public class DynamicWithdrawDepositController {

    @Autowired
    private WithdrawRepo withdrawRepo;

    @Autowired
    private WithdrawDepositService withdrawDepositService;

    @Autowired
    private AccountRepo accountRepo;

    Withdraw_Deposit withdraw_Deposit = new Withdraw_Deposit();

    @GetMapping("View-Withdraw-Id")
    public ModelAndView GetListOfWithrawbyId() {
        ModelAndView mav = new ModelAndView("ViewWithdrawDeposit.html");

        // Get the logged-in user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();

        // Retrieve the list of transactions for the logged-in user
        List<Withdraw_Deposit> listOfTransfers = withdrawRepo.findAllByRelatedAccountUser(user);

        mav.addObject("transactions", listOfTransfers);
        return mav;
    }

    @GetMapping("View-Withdraw")
    public ModelAndView GetListOfWithdraw() {
        ModelAndView mav = new ModelAndView("ViewWithdrawDeposit.html");
        List<Withdraw_Deposit> ListOfTransfers = withdrawRepo.findAll();
        mav.addObject("transactions", ListOfTransfers);

        return mav;
    }

    @GetMapping("Add-Withdraw")
    public ModelAndView getwithdrawDepositForm() {
        ModelAndView mav = new ModelAndView("AddWithdrawDeposit.html");
        Withdraw_Deposit withdraw_Deposit = new Withdraw_Deposit();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();

        withdraw_Deposit.setDate(LocalDate.now().toString()); // Set the current date

        mav.addObject("transactions", withdraw_Deposit);
        mav.addObject("accountList", accountRepo.findAllByUser(user)); // Add account list as a model attribute
        return mav;
    }

    @PostMapping("/Save-Withdraw")
    public String saveWithdraw(
            @RequestParam("accountId") Long accountId,
            @RequestParam("amount") double amount,
            @RequestParam("type") Withdraw_Deposit.Type type,
            @RequestParam("date") String date) {

        withdrawDepositService.SaveWithdraw(amount, date, type, accountId, TransactionType.WITHDRAW_DEPOSIT);

        return "redirect:/thymeleaf/View-Withdraw-Id";
    }

}
