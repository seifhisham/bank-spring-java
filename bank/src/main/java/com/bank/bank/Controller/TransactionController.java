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

// import com.bank.bank.Models.Transaction;

// import com.bank.bank.Repositories.TransRepo;
// import com.bank.bank.Services.LoggingService;


// @RestController
// @RequestMapping("/Transaction")
// public class TransactionController {

//     @Autowired
//     private LoggingService loggingService;

//     @Autowired
//     private TransRepo transRepo;

//     @GetMapping("")
//     public ResponseEntity GetTransaction() {
//         loggingService.log("User fetched all Transctipts");
//         List<Transaction> Trans = this.transRepo.findAll();
//         return new ResponseEntity(Trans, HttpStatus.OK);
//     }

//     @PostMapping("")
//     public ResponseEntity<Transaction> createTransaction(@RequestBody Map<String, String> body) {
//         Transaction transaction = new Transaction();

//         transaction.setAmount(Double.parseDouble(body.get("amount")));
//         transaction.setTransactionType(Transaction.TransactionType.valueOf(body.get("transactionType")));
//         transaction.setDate(String.valueOf(body.get("Date")));

//         this.transRepo.save(transaction);
//         return new ResponseEntity<>(transaction, HttpStatus.CREATED);
//     }

// }
