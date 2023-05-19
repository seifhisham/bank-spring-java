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
import com.bank.bank.Models.Transaction;
import com.bank.bank.Models.Transfers;
import com.bank.bank.Models.User;
import com.bank.bank.Repositories.AccountRepo;
import com.bank.bank.Repositories.TransfersRepo;
import com.bank.bank.Services.TransferService;

@Controller
@RequestMapping("/thymeleaf")
public class DynamicTransferController {
    @Autowired
    private TransfersRepo transfersRepo;
    @Autowired
    private AccountRepo accountRepo;
    @Autowired
    private TransferService transferService;

    @GetMapping("View-Transfer")
public ModelAndView getAccountList() {
    ModelAndView mav = new ModelAndView("ViewTransfer.html");

    // Get the logged-in user
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    User user = (User) authentication.getPrincipal();

    // Retrieve the transfers for the logged-in user
    List<Transfers> transfersList = transfersRepo.findBySourceAccountUserOrDestinationAccountUser(user, user);

    mav.addObject("transfers", transfersList);
    return mav;
}


    @GetMapping("Add-Transfer")
    public ModelAndView getAddAccountForm() {
        ModelAndView mav = new ModelAndView("AddTransfer.html");
        Transfers transfers = new Transfers();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
       
        mav.addObject("transfers", transfers);
        mav.addObject("sourceAccount", accountRepo.findAllByUser(user));
        mav.addObject("destinationAccount", accountRepo.findAll());
        return mav;
    }

    @PostMapping("/Save-Transfer")
    public String saveAccount(
            @RequestParam("amount") double amount,
            @RequestParam("accountId") Long accountId,
            @RequestParam("ReceiverId") Long ReceiverId) {
            LocalDate date = LocalDate.now();
        transferService.SaveWithdraw(amount,date.toString(), accountId, ReceiverId, Transaction.TransactionType.TRANSFERS);

        return "redirect:/thymeleaf/View-Transfer";
    }
}
