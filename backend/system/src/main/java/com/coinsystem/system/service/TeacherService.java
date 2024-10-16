package com.coinsystem.system.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coinsystem.system.DTO.TeacherDTO;
import com.coinsystem.system.exception.UserNotFoundException;
import com.coinsystem.system.mappers.UsersMapper;
import com.coinsystem.system.model.Teacher;
import com.coinsystem.system.repository.TeacherRepository;
import com.coinsystem.system.service.interfaces.ITeacherService;

@Service
public class TeacherService implements ITeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    @Override
    public Teacher register(TeacherDTO teacherDTO) {
        Teacher teacher = UsersMapper.TeacherDtoToModel(teacherDTO);
        teacherRepository.save(teacher);
        return teacher;
    }

    @Override
    public List<Teacher> getAllTeacher() {
       return teacherRepository.findAll();
    }

    @Override
    public Teacher getTeacherById(Long id) {
        Optional<Teacher> teacher = teacherRepository.findById(id);

        return teacher.orElseThrow(() -> new UserNotFoundException("User with id " + id + " not found."));
    }

    @Override
    public Teacher update(Long id, TeacherDTO teacherDTO) {
        Optional<Teacher> optional = teacherRepository.findById(id);
        if (optional.isPresent()) {
            Teacher existingTeacher = optional.get();
            existingTeacher.setAddress(teacherDTO.address());
            existingTeacher.setPhoneNumber(teacherDTO.phone_number());
            existingTeacher.setPassword(teacherDTO.password());
            existingTeacher.setSalary(teacherDTO.salary());
            existingTeacher.setDepartment(teacherDTO.department());

            teacherRepository.save(existingTeacher);
            return existingTeacher;
        } else {
            throw new UserNotFoundException("User with id " + id + " not found.");
        }
    }

    @Override
    public boolean delete(Long id) {
       Optional<Teacher> optional = teacherRepository.findById(id);
        if (optional.isPresent()) {
            teacherRepository.delete(optional.get());
            return true;
        }

        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }
    
}
