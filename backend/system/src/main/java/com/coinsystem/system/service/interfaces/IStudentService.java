package com.coinsystem.system.service.interfaces;

import com.coinsystem.system.DTO.StudentDTO;
import com.coinsystem.system.model.Student;

import java.util.List;

public interface IStudentService {
    Student register(StudentDTO teacherDTO);

    Student registerWithTeacher(StudentDTO studentDTO);

    List<Student> getAllStudent();

    Student getStudentById(Long id);

    Student update(Long id, StudentDTO studentDTO);

    boolean delete(Long id);
}
