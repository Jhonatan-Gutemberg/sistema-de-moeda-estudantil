package com.coinsystem.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coinsystem.system.model.Classroom;

@Repository
public interface ClassroomRepository  extends JpaRepository<Classroom, Long>{
    
}
