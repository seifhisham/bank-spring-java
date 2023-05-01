package com.bank.bank.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bank.bank.Models.Manager;
import com.bank.bank.Models.User;

public interface UserRepo extends JpaRepository<User, Long>{
    
}
