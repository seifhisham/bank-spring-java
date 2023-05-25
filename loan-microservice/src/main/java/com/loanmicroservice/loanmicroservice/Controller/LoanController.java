package com.loanmicroservice.loanmicroservice.Controller;
import com.loanmicroservice.loanmicroservice.Models.Loan;
import com.loanmicroservice.loanmicroservice.Repositories.LoanRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/loan")
public class LoanController {
    @Autowired
private LoanRepo loanRepo;

    @GetMapping("/loans")
    public ResponseEntity<List<Loan>> getAllLoans() {
        List<Loan> loans = loanRepo.findAll();
        return new ResponseEntity<>(loans, HttpStatus.OK);
    }

    // Other controller methods...

    @PostMapping("")
    public ResponseEntity<Loan> createLoan(@RequestBody Map<String, String> request_body) {
        Loan loan = new Loan();
    
        loan.setRelatedAccount(Long.parseLong(request_body.get("relatedAccount")));
        loan.setAmount(Double.parseDouble(request_body.get("amount")));
        loan.setStatus(Loan.LoanStatus.valueOf(request_body.get("status")));
    
        Loan savedLoan = loanRepo.save(loan);
    
        return new ResponseEntity<>(savedLoan, HttpStatus.CREATED);
    }
    


    
}
