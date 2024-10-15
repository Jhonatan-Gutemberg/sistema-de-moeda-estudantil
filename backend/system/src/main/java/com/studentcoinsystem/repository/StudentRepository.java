package com.studentcoinsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.studentcoinsystem.model.Student;

//@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

}