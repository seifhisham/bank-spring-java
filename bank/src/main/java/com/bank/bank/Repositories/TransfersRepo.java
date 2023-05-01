package com.bank.bank.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.bank.Models.Transfers;

@Repository
public interface TransfersRepo extends JpaRepository<Transfers, Long>{
    
}
