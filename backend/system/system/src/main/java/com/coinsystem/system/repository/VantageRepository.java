package com.coinsystem.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coinsystem.system.model.Vantage;

@Repository
public interface VantageRepository extends JpaRepository<Vantage, Long>{
    
}
