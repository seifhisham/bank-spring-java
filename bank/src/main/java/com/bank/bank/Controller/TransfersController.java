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
// import com.bank.bank.Repositories.TransfersRepo;
// import com.bank.bank.Services.LoggingService;


// @RestController
// @RequestMapping("/transfers")
// public class TransfersController {

//     @Autowired
//     private LoggingService loggingService;
//     @Autowired
//     private TransfersRepo transfersRepo;

//     @GetMapping("")
//     public ResponseEntity GetTransfers() {
//         loggingService.log("User fetched all accounts");
//         List<Transfers> transfers = this.transfersRepo.findAll();
//         return new ResponseEntity(transfers, HttpStatus.OK);
//     }

//     @PostMapping("")
//     public ResponseEntity<Account> CreateTransfers(@RequestBody Map<String, String> body) {
//         Transfers transfers = new Transfers();

//         Account senderaccount=new Account();
//         Account receiveraccount=new Account();

//         transfers.setAmount(Double.parseDouble(body.get("amount")));
//         transfers.setDate(String.valueOf(body.get("Date")));
//         transfers.setTransactionType(Transfers.TransactionType.valueOf(body.get("TransactionType")));
//         senderaccount.setId(Long.parseLong(body.get("senderid")));
//         receiveraccount.setId(Long.parseLong(body.get("receiverid")));
        
//         this.transfersRepo.save(transfers);
//         return new ResponseEntity(transfers, HttpStatus.CREATED);
//     }
// }
