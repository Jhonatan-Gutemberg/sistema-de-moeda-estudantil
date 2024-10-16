package com.coinsystem.system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coinsystem.system.DTO.TeacherDTO;
import com.coinsystem.system.controller.ApiResponse.ApiResponse;
import com.coinsystem.system.model.Student;
import com.coinsystem.system.model.Teacher;
import com.coinsystem.system.service.interfaces.ITeacherService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    private ITeacherService teacherService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<Teacher>> register(@RequestBody @Valid TeacherDTO TeacherDTO) {
        try {
            Teacher teacher = teacherService.register(TeacherDTO);
            ApiResponse<Teacher> response = new ApiResponse<Teacher>(true, "User registered succesfully", teacher);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            ApiResponse<Teacher> errorResponse = new ApiResponse<Teacher>(false, e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<Teacher>>> getAllTeacher() {
        try {
            List<Teacher> teacher = teacherService.getAllTeacher();
            ApiResponse<List<Teacher>> response = new ApiResponse<>(true, "All Teacher fetched successfully", teacher);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ApiResponse<List<Teacher>> errorResponse = new ApiResponse<>(false, e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Teacher>> getUserById(@PathVariable Long id) {
        try {
            Teacher Teacher = teacherService.getTeacherById(id);
            if (Teacher != null) {
                ApiResponse<Teacher> response = new ApiResponse<>(true, "Teacher found successfully", Teacher);
                return ResponseEntity.ok(response);
            } else {
                ApiResponse<Teacher> response = new ApiResponse<>(false, "Teacher not found", null);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        } catch (Exception e) {
            ApiResponse<Teacher> errorResponse = new ApiResponse<>(false, e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @GetMapping("/{id}/students")
    public ResponseEntity<ApiResponse<List<Student>>> getStudentsByTeacherId(@PathVariable Long id) {
        try {
            List<Student> students = teacherService.getStudentByTeacherId(id);
            ApiResponse<List<Student>> response = new ApiResponse<>(true, "Students retrieved successfully", students);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ApiResponse<List<Student>> errorResponse = new ApiResponse<>(false, e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<Teacher>> update(@PathVariable Long id,
            @RequestBody @Valid TeacherDTO teacherDTO) {
        try {
            Teacher updatedTeacher = teacherService.update(id, teacherDTO);
            if (updatedTeacher != null) {
                ApiResponse<Teacher> response = new ApiResponse<>(true, "Teacher updated successfully", updatedTeacher);
                return ResponseEntity.ok(response);
            } else {
                ApiResponse<Teacher> response = new ApiResponse<>(false, "Teacher not found", null);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        } catch (Exception e) {
            ApiResponse<Teacher> errorResponse = new ApiResponse<>(false, e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        try {
            boolean isDeleted = teacherService.delete(id);
            if (isDeleted) {
                ApiResponse<Void> response = new ApiResponse<>(true, "Teacher deleted successfully", null);
                return ResponseEntity.ok(response);
            } else {
                ApiResponse<Void> response = new ApiResponse<>(false, "Teacher not found", null);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        } catch (Exception e) {
            ApiResponse<Void> errorResponse = new ApiResponse<>(false, e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

}
