package com.studentcoinsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.studentcoinsystem.model.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long>{
    
}
