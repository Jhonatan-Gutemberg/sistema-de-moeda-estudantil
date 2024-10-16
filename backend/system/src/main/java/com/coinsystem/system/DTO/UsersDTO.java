package com.coinsystem.system.DTO;

import com.coinsystem.system.enums.UsersType;


public record UsersDTO(
        String name,
        String email,
        UsersType type,
        String phoneNumber,
        String password,
        String address) {

}
