package com.coinsystem.system.DTO;

import com.coinsystem.system.enums.UsersType;

public record TeacherDTO(
        String name,
        String email,
        UsersType type,
        String phone_number,
        double salary,
        String department,
        String password,
        String address,
        WalletDTO wallet) {

}
