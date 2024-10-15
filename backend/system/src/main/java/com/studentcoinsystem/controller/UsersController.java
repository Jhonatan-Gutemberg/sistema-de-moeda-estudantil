package com.studentcoinsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;


import com.studentcoinsystem.dto.UsersDTO;
import com.studentcoinsystem.model.Users;
import com.studentcoinsystem.service.interfaces.IUsersService;
import com.studentcoinsystem.controller.ApiResponse.ApiResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private IUsersService usersService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<Users>> register(
            @RequestBody @Valid UsersDTO usersDTO) {
        try {
            Users result = usersService.register(usersDTO);
            ApiResponse<Users> response = new ApiResponse<>(true, "User registered successfully", result);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            ApiResponse<Users> errorResponse = new ApiResponse<>(false, e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }
    
}
