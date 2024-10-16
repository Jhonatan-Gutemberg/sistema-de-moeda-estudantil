package com.coinsystem.system.DTO;

import com.coinsystem.system.enums.UsersType;

public record StudentDTO(
        String name,
        String email,
        UsersType type,
        String phone_number,
        String cpf,
        String rg,
        String password,
        String address,
        Long id_teacher,
        WalletDTO wallet) {

}
