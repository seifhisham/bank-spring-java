package com.bank.bank.Controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.bank.bank.Models.Transaction;
import com.bank.bank.Models.Withdraw_Deposit;
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

    @GetMapping("view-withdraw")
    public ModelAndView getListOfTransfers() {
        ModelAndView mav = new ModelAndView("WithdrawDeposit.html");
        List<Withdraw_Deposit> ListOfTransfers = withdrawRepo.findAll();
        mav.addObject("transactions", ListOfTransfers);
        return mav;
    }

    @GetMapping("add-withdraw")
    public ModelAndView getwithdrawDepositForm() {
        ModelAndView mav = new ModelAndView("AddWithdrawDeposit.html");
        Withdraw_Deposit withdraw_Deposit = new Withdraw_Deposit();

        mav.addObject("transactions", withdraw_Deposit);
        mav.addObject("account", accountRepo.findAll());
        return mav;
    }

    @PostMapping("/save-withdraw")
    public String saveWithdraw(
            @RequestParam("accountId") Long accountId,
            @RequestParam("amount") double amount,
            @RequestParam("type") Withdraw_Deposit.Type type,
            @RequestParam("date") String date)

            {
                Withdraw_Deposit withdraw_Deposit = new Withdraw_Deposit();
                withdraw_Deposit.setTransactionType(Transaction.TransactionType.WITHDRAW_DEPOSIT);


        withdrawDepositService.SaveWithdraw(amount, date, type, accountId);

        return "redirect:/thymeleaf/view-withdraw";
    }

}
