package com.studentcoinsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.studentcoinsystem.model.PartnerCompany;

@Repository
public interface PartnerCompanyRepository extends JpaRepository<PartnerCompany, Long> {
    
}
