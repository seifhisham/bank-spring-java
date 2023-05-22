package com.bank.bank.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.bank.Models.Account;
import com.bank.bank.Models.Loan;
import com.bank.bank.Models.Loan.LoanStatus;
import com.bank.bank.Repositories.AccountRepo;
import com.bank.bank.Repositories.LoanRepo;

@Service
public class LoanService {
    @Autowired
    private AccountRepo accountRepository;
    @Autowired
    private LoanRepo loanRepo;

    public void SaveLoan(double amount, Long RelatedID, LoanStatus loanStatus) {
        Account account = accountRepository.findById(RelatedID)
                .orElseThrow(() -> new IllegalArgumentException("Invalid account ID: " + RelatedID));

        Loan loan = new Loan();

        loan.setAmount(amount);
        loan.setRelatedAccount(account);
        loan.setStatus(loanStatus.WAITING);

        loanRepo.save(loan);

    }

}
