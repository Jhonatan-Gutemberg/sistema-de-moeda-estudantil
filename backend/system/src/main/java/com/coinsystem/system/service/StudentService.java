package com.coinsystem.system.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coinsystem.system.DTO.StudentDTO;
import com.coinsystem.system.exception.UserNotFoundException;
import com.coinsystem.system.mappers.UsersMapper;
import com.coinsystem.system.model.Student;
import com.coinsystem.system.repository.StudentRepository;
import com.coinsystem.system.service.interfaces.IStudentService;

@Service
public class StudentService implements IStudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Student register(StudentDTO studentDTO) {
        Student student = UsersMapper.StudentDtoToModel(studentDTO);
        studentRepository.save(student);
        return student;
    }

    @Override
    public List<Student> getAllStudent() {
        return studentRepository.findAll();
    }

    @Override
    public Student getStudentById(Long id) {
        Optional<Student> student = studentRepository.findById(id);

        return student.orElseThrow(() -> new UserNotFoundException("User with id " + id + " not found."));
    }

    @Override
    public Student update(Long id, StudentDTO studentDTO) {
        Optional<Student> optional = studentRepository.findById(id);
        if (optional.isPresent()) {
            Student existingStudent = optional.get();
            existingStudent.setAddress(studentDTO.address());
            existingStudent.setPhoneNumber(studentDTO.phoneNumber());
            existingStudent.setPassword(studentDTO.password());

            studentRepository.save(existingStudent);
            return existingStudent;
        } else {
            throw new UserNotFoundException("User with id " + id + " not found.");
        }
    }

    @Override
    public boolean delete(Long id) {
        Optional<Student> optional = studentRepository.findById(id);
        if (optional.isPresent()) {
            studentRepository.delete(optional.get());
            return true;
        }

        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

}
