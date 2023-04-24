package com.bank.bank.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.bank.Models.Transaction;

@Repository
public interface TransRepo extends JpaRepository<Transaction, Long> {

}
