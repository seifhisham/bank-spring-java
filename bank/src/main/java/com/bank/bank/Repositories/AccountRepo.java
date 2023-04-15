package com.bank.bank.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.bank.Models.Account;

@Repository
public interface AccountRepo extends JpaRepository<Account, Long>{
    
}
