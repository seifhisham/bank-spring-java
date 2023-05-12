package com.bank.bank.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.bank.Models.AccountType;

@Repository
public interface AccountTypeRepo extends JpaRepository<AccountType, Long> {
    
}