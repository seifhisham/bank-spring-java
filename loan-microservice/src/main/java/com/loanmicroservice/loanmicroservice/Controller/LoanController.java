package com.loanmicroservice.loanmicroservice.Controller;

import com.loanmicroservice.loanmicroservice.Models.Loan;
import com.loanmicroservice.loanmicroservice.Repositories.LoanRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;
import java.util.Optional;

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

    @PutMapping("/Accept/{id}")
    public ResponseEntity<Loan> AcceptLoan(@PathVariable("id") Long id) {
        Optional<Loan> optionalLoan = loanRepo.findById(id);

        if (optionalLoan.isPresent()) {
            Loan loan = optionalLoan.get();

            if (loan.getStatus() == Loan.LoanStatus.WAITING) {
                loan.setStatus(Loan.LoanStatus.APPROVED);
                Loan acceptance = loanRepo.save(loan);
                return new ResponseEntity<>(acceptance, HttpStatus.ACCEPTED);
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/Decline/{id}")
    public ResponseEntity<Loan> DeclineLoan(@PathVariable("id") Long id) {
        Optional<Loan> optionalLoan = loanRepo.findById(id);

        if (optionalLoan.isPresent()) {
            Loan loan = optionalLoan.get();

            if (loan.getStatus() == Loan.LoanStatus.WAITING) {
                loan.setStatus(Loan.LoanStatus.DECLINED);
                Loan acceptance = loanRepo.save(loan);
                return new ResponseEntity<>(acceptance, HttpStatus.ACCEPTED);
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
