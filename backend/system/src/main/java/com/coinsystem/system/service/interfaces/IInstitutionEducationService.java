package com.coinsystem.system.service.interfaces;


import com.coinsystem.system.DTO.InstitutionEducationDTO;
import com.coinsystem.system.model.InstitutionEducation;

import java.util.List;

public interface IInstitutionEducationService {
    InstitutionEducation register(InstitutionEducationDTO institutionEducationDTO);

    List<InstitutionEducation> getAllInstitutionEducation();

    InstitutionEducation getInstitutionEducationById(Long id);

    InstitutionEducation update(Long id, InstitutionEducationDTO institutionEducationDTO);

    boolean delete(Long id);
}
