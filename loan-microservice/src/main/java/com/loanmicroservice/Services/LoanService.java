package com.loanmicroservice.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.loanmicroservice.loanmicroservice.Models.Loan;
import com.loanmicroservice.loanmicroservice.Models.Loan.LoanStatus;

import com.loanmicroservice.loanmicroservice.Repositories.LoanRepo;


@Service
public class LoanService {

    @Autowired
    private LoanRepo loanRepo;

  


}
