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

import com.coinsystem.system.DTO.UsersDTO;
import com.coinsystem.system.controller.ApiResponse.ApiResponse;
import com.coinsystem.system.model.Users;
import com.coinsystem.system.service.interfaces.IUsersSrevice;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UsersController {
    @Autowired
    private IUsersSrevice usersSrevice;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<Users>> register(@RequestBody @Valid UsersDTO usersDTO) {
        try {
            Users users = usersSrevice.registerUsers(usersDTO);
            ApiResponse<Users> response = new ApiResponse<Users>(true, "User registered succesfully", users);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            ApiResponse<Users> errorResponse = new ApiResponse<Users>(false, e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<Users>>> getAllUsers() {
        try {
            List<Users> users = usersSrevice.getAllUsers();
            ApiResponse<List<Users>> response = new ApiResponse<>(true, "All users fetched successfully", users);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ApiResponse<List<Users>> errorResponse = new ApiResponse<>(false, e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Users>> getUserById(@PathVariable Long id) {
        try {
            Users Users = usersSrevice.getUserById(id);
            if (Users != null) {
                ApiResponse<Users> response = new ApiResponse<>(true, "Users found successfully", Users);
                return ResponseEntity.ok(response);
            } else {
                ApiResponse<Users> response = new ApiResponse<>(false, "Users not found", null);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        } catch (Exception e) {
            ApiResponse<Users> errorResponse = new ApiResponse<>(false, e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<Users>> update(@PathVariable Long id, @RequestBody @Valid UsersDTO usersDTO) {
        try {
            Users updatedUsers = usersSrevice.update(id, usersDTO);
            if (updatedUsers != null) {
                ApiResponse<Users> response = new ApiResponse<>(true, "Users updated successfully", updatedUsers);
                return ResponseEntity.ok(response);
            } else {
                ApiResponse<Users> response = new ApiResponse<>(false, "Users not found", null);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        } catch (Exception e) {
            ApiResponse<Users> errorResponse = new ApiResponse<>(false, e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        try {
            boolean isDeleted = usersSrevice.delete(id);
            if (isDeleted) {
                ApiResponse<Void> response = new ApiResponse<>(true, "Users deleted successfully", null);
                return ResponseEntity.ok(response);
            } else {
                ApiResponse<Void> response = new ApiResponse<>(false, "Users not found", null);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        } catch (Exception e) {
            ApiResponse<Void> errorResponse = new ApiResponse<>(false, e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

}
