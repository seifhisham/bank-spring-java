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

import com.bank.bank.Models.Account;
import com.bank.bank.Models.Loan;
import com.bank.bank.Models.User;
import com.bank.bank.Models.Loan.LoanStatus;
import com.bank.bank.Repositories.AccountRepo;
import com.bank.bank.Repositories.LoanRepo;
import com.bank.bank.Services.LoanService;

@Controller
@RequestMapping("/thymeleaf")
public class DynamicLoanController {
    @Autowired
    private LoanRepo loanRepo;

    @Autowired
    private AccountRepo accountRepo;

    @Autowired
    private LoanService loanService;

    @GetMapping("View-Loan-Id")
    public ModelAndView GetLoansListID() {
        ModelAndView mav = new ModelAndView("ViewLoans.html");

        // Get the logged-in user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();

        // Retrieve the transfers for the logged-in user
        List<Loan> LoanList = loanRepo.findAllByRelatedAccountUser(user);

        mav.addObject("Loans", LoanList);
        return mav;
    }

    @GetMapping("View-Loan")
    public ModelAndView GetLoansList() {
        ModelAndView mav = new ModelAndView("ViewAllLoans.html");
        List<Loan> LoanList = loanRepo.findAll();
        mav.addObject("Loans", LoanList);

        return mav;
    }

    @GetMapping("Add-Loan")
    public ModelAndView getAddPostForm() {
        ModelAndView mav = new ModelAndView("AddLoan.html");
        Loan loan = new Loan();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        mav.addObject("accountList", accountRepo.findAllByUser(user));
        mav.addObject("Loans", loan);
        return mav;
    }

    @PostMapping("/Save-Loan")
    public String SaveLoan(
            @RequestParam("accountId") Long accountId,
            @RequestParam("amount") double amount) {

        loanService.SaveLoan(amount, accountId, LoanStatus.WAITING);

        return "redirect:/thymeleaf/View-Loan-Id";
    }

}
