package com.studentcoinsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.studentcoinsystem.model.Teacher;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    
}
