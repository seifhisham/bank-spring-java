package com.bank.bank.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.bank.Models.Manager;

@Repository
public interface ManagerRepo extends JpaRepository<Manager, Long>{
    
}
