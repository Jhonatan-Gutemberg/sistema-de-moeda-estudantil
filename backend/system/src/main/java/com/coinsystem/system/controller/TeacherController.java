package com.coinsystem.system.controller;

import java.util.ArrayList;
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
import com.coinsystem.system.controller.ApiResponse.ApiResponseLogin;
import com.coinsystem.system.infra.ITokenService;
import com.coinsystem.system.mappers.TeacherMapper;
import com.coinsystem.system.model.Student;
import com.coinsystem.system.model.Teacher;
import com.coinsystem.system.service.interfaces.ITeacherService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/teacher")
@RequiredArgsConstructor
public class TeacherController {

    @Autowired
    private ITeacherService teacherService;
    private final ITokenService tokenService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponseLogin<TeacherDTO>> register(@RequestBody @Valid TeacherDTO TeacherDTO) {
        try {
            Teacher teacher = teacherService.register(TeacherDTO);
            String token = this.tokenService.generateToken(teacher);

            TeacherDTO responseTeacherDTO = TeacherMapper.teacherToTeacherDTO(teacher);

            ApiResponseLogin<TeacherDTO> response = new ApiResponseLogin<TeacherDTO>(true, "User registered succesfully", responseTeacherDTO, token);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            ApiResponseLogin<TeacherDTO> errorResponse = new ApiResponseLogin<TeacherDTO>(false, e.getMessage(), null,null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<TeacherDTO>>> getAllTeacher() {
        try {
            List<Teacher> teacher = teacherService.getAllTeacher();
            List<TeacherDTO> responseTeacherDTO = new ArrayList<>();

            for (Teacher t : teacher) {
                responseTeacherDTO.add(TeacherMapper.teacherToTeacherDTO(t));
            }

            ApiResponse<List<TeacherDTO>> response = new ApiResponse<>(true, "All Teacher fetched successfully", responseTeacherDTO);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ApiResponse<List<TeacherDTO>> errorResponse = new ApiResponse<>(false, e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<TeacherDTO>> getUserById(@PathVariable Long id) {
        try {
            Teacher Teacher = teacherService.getTeacherById(id);
            if (Teacher != null) {
                TeacherDTO TeacherDTO = TeacherMapper.teacherToTeacherDTO(Teacher);
                ApiResponse<TeacherDTO> response = new ApiResponse<>(true, "Teacher found successfully", TeacherDTO);
                return ResponseEntity.ok(response);
            } else {
                ApiResponse<TeacherDTO> response = new ApiResponse<>(false, "Teacher not found", null);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        } catch (Exception e) {
            ApiResponse<TeacherDTO> errorResponse = new ApiResponse<>(false, e.getMessage(), null);
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

    @GetMapping("/{id}/wallet")
    public ResponseEntity<ApiResponse<Integer>> getWalletByTeacherId(@PathVariable Long id) {
        try {
            int coins = teacherService.getCoinsByTeacherId(id);
            ApiResponse<Integer> response = new ApiResponse<>(true, "Coins retrieved successfully", coins);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ApiResponse<Integer> errorResponse = new ApiResponse<>(false, e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<TeacherDTO>> update(@PathVariable Long id,
            @RequestBody @Valid TeacherDTO teacherDTO) {
        try {
            Teacher updatedTeacher = teacherService.update(id, teacherDTO);
            if (updatedTeacher != null) {
                TeacherDTO responseTeacherDTO = TeacherMapper.teacherToTeacherDTO(updatedTeacher);

                ApiResponse<TeacherDTO> response = new ApiResponse<>(true, "Teacher updated successfully", responseTeacherDTO);
                return ResponseEntity.ok(response);
            } else {
                ApiResponse<TeacherDTO> response = new ApiResponse<>(false, "Teacher not found", null);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        } catch (Exception e) {
            ApiResponse<TeacherDTO> errorResponse = new ApiResponse<>(false, e.getMessage(), null);
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
