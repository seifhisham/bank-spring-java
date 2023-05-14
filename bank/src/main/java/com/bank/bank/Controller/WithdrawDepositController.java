// package com.bank.bank.Controller;

// import java.util.List;
// import java.util.Map;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// import com.bank.bank.Models.Account;
// import com.bank.bank.Models.Transfers;
// import com.bank.bank.Models.User;
// import com.bank.bank.Models.Withdraw_Deposit;

// import com.bank.bank.Repositories.TransfersRepo;
// import com.bank.bank.Repositories.WithdrawRepo;
// import com.bank.bank.Services.LoggingService;

// @RestController
// @RequestMapping("/WithdrawDeposit")
// public class WithdrawDepositController {
//     @Autowired
//     private LoggingService loggingService;
//     @Autowired
//     private WithdrawRepo withdrawRepo;

//     @GetMapping("")
//     public ResponseEntity GetWithdrawDeposit() {
//         loggingService.log("User fetched all accounts");
//         List<Withdraw_Deposit> Withdraw_Deposit = this.withdrawRepo.findAll();
//         return new ResponseEntity(Withdraw_Deposit, HttpStatus.OK);
//     }

//     @PostMapping("")
//     public ResponseEntity<Withdraw_Deposit> CreateWithdrawDeposit(@RequestBody Map<String, String> body) {

//         Withdraw_Deposit withdraw = new Withdraw_Deposit();
//         Account relatedAccount = new Account();

//         withdraw.setAmount(Double.parseDouble(body.get("amount")));
//         withdraw.setDate(String.valueOf(body.get("date")));
//         withdraw.setTransactionType(Withdraw_Deposit.TransactionType.valueOf(body.get("transactionType")));
//         relatedAccount.setId(Long.parseLong(body.get("relatedID")));

//         this.withdrawRepo.save(withdraw);
//         return new ResponseEntity<>(withdraw, HttpStatus.CREATED);
//     }

// }
