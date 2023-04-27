package com.bank.bank.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bank.bank.Models.Manager;

public interface UserRepo extends JpaRepository<Manager, Long>{
    
}
