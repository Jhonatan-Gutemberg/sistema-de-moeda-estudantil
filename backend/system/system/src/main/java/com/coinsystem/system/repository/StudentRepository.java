package com.coinsystem.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coinsystem.system.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

}
