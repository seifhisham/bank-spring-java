package com.loanmicroservice.loanmicroservice.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.loanmicroservice.loanmicroservice.Models.Loan;

@Repository
public interface LoanRepo extends JpaRepository<Loan, Long> {

}