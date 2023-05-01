package com.bank.bank.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.bank.Models.Withdraw_Deposit;

public interface WithdrawRepo extends JpaRepository<Withdraw_Deposit, Long> {

}
