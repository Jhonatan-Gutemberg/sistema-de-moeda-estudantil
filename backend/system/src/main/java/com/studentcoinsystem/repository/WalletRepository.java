package com.studentcoinsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.studentcoinsystem.model.Wallet;

@Repository
public interface WalletRepository extends JpaRepository<Wallet,Long> {
    
}
