package com.bank.bank.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bank.bank.Models.User;
import com.bank.bank.Models.Withdraw_Deposit;

public interface WithdrawRepo extends JpaRepository<Withdraw_Deposit, Long> {
    List<Withdraw_Deposit> findAllByRelatedAccountUser(User user);
}

