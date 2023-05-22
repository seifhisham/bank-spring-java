package com.bank.bank.Repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.bank.bank.Models.Loan;
import com.bank.bank.Models.User;

@Repository
public interface LoanRepo extends JpaRepository<Loan, Integer> {

    List<Loan> findAllByRelatedAccountUser(User user);

}
