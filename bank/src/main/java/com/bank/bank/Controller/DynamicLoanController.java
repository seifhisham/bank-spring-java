package com.bank.bank.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bank.bank.Models.Account;
import com.bank.bank.Models.Loan;
import com.bank.bank.Models.Loan.LoanStatus;
import com.bank.bank.Repositories.AccountRepo;
import com.bank.bank.Models.User;
import com.bank.bank.Services.LoanService;

@Controller
@RequestMapping("/thymeleaf")
public class DynamicLoanController {

    @Autowired
    private LoanService loanService;

    @Autowired
    private AccountRepo accountRepo;

    @GetMapping("View-Loan-Id")
    public ModelAndView getLoansListID() {
        ModelAndView mav = new ModelAndView("ViewLoans.html");

        // Get the logged-in user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();

        // Retrieve the loans for the logged-in user
        List<Loan> loanList = loanService.findAllByRelatedAccountUser(user);

        mav.addObject("loans", loanList);
        return mav;
    }

    @GetMapping("View-Loan")
    public ModelAndView getLoansList() {
        ModelAndView mav = new ModelAndView("ViewAllLoans.html");
        List<Loan> loanList = loanService.findAll();
        mav.addObject("loans", loanList);

        return mav;
    }

    @GetMapping("Add-Loan")
    public ModelAndView getAddLoanForm(@AuthenticationPrincipal User user) {
        ModelAndView mav = new ModelAndView("AddLoan.html");
       
        Loan loan = new Loan();

        mav.addObject("loan", loan);
        mav.addObject("accountList", accountRepo.findAllByUser(user));
        return mav;
    }

    @PostMapping("/Save-Loan")
    public ResponseEntity<String> saveLoan(
            @RequestParam("accountId") Long accountId,
            @RequestParam("amount") double amount) {
    
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
    
        Loan savedLoan = loanService.SaveLoan(amount, accountId, LoanStatus.WAITING);
    
        if (savedLoan != null) {
            return ResponseEntity.ok().body("Loan saved successfully");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to save loan");
        }
    }
    

    
    
}
