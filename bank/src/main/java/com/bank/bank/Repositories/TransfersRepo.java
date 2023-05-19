package com.bank.bank.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.bank.Models.Transfers;
import com.bank.bank.Models.User;

@Repository
public interface TransfersRepo extends JpaRepository<Transfers, Long>{

    List<Transfers> findBySourceAccountUserOrDestinationAccountUser(User user, User user2);
    
}
