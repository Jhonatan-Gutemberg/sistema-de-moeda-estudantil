package com.coinsystem.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coinsystem.system.model.PartnerCompany;

@Repository
public interface PartnerCompanyRepository extends JpaRepository<PartnerCompany, Long> {
    
}
