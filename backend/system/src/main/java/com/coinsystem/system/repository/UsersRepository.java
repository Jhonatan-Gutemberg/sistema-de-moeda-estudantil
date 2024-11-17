package com.coinsystem.system.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coinsystem.system.model.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long>{

    Optional<Users> findByName(String login);
    Optional<Users> findByEmail(String login);

    
}
