package com.bank.bank.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.bank.Models.Account;
import com.bank.bank.Models.User;

@Repository
public interface AccountRepo extends JpaRepository<Account, Long> {

    List<Account> findAllByUser(User user);
}
