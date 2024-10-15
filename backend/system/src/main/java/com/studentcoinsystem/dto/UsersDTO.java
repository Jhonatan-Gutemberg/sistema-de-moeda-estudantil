package com.studentcoinsystem.dto;

public record UsersDTO(
        Long id,
        String name,
        String email,
        String phoneNumber,
        String password,
        String address) {

}
