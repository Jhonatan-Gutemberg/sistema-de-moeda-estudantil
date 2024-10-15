package com.coinsystem.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coinsystem.system.model.InstitutionEducation;

@Repository
public interface InstitutionEducationRepository extends JpaRepository<InstitutionEducation, Long> {
    
}
