package com.coinsystem.system.DTO;

import com.coinsystem.system.enums.UsersType;

public record TeacherDTO(
        String name,
        String email,
        UsersType type,
        String phoneNumber,
        double salary,
        String department,
        String password,
        String address) {

}
