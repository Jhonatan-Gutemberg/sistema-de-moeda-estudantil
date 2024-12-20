package com.coinsystem.system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coinsystem.system.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByTeacherId(Long idTeacher);

    Student findByEmail(String email);

}
