package com.coinsystem.system.DTO;

import com.coinsystem.system.enums.UsersType;

public record InstitutionEducationResponseDTO(
    Long id,
    String name,
    String email,
    UsersType type,
    String phoneNumber,
    String address,
    String cnpj
) {
    
}
