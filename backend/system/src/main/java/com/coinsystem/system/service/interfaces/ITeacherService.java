package com.coinsystem.system.service.interfaces;

import com.coinsystem.system.DTO.TeacherDTO;
import com.coinsystem.system.model.Student;
import com.coinsystem.system.model.Teacher;

import java.util.List;

public interface ITeacherService {
    Teacher register(TeacherDTO teacherDTO);

    List<Teacher> getAllTeacher();

    Teacher getTeacherById(Long id);
    
    List<Student> getStudentByTeacherId(Long idTeacher);

    Teacher update(Long id, TeacherDTO teacherDTO);

    boolean delete(Long id);
}
