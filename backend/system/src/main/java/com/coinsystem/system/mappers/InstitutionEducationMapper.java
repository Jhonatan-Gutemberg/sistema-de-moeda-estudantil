package com.coinsystem.system.mappers;

import com.coinsystem.system.DTO.InstitutionEducationResponseDTO;
import com.coinsystem.system.model.InstitutionEducation;

public class InstitutionEducationMapper {
    public static InstitutionEducationResponseDTO institutionEducationToInstitutionEducationResponseDTO(InstitutionEducation institutionEducation) {
        return new InstitutionEducationResponseDTO(
                institutionEducation.getId(),
                institutionEducation.getName(),
                institutionEducation.getEmail(),
                institutionEducation.getType(),
                institutionEducation.getPhoneNumber(),
                institutionEducation.getAddress(),
                institutionEducation.getCnpj()
        );
    }
}
