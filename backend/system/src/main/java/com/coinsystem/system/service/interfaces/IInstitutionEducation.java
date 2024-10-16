package com.coinsystem.system.service.interfaces;


import com.coinsystem.system.DTO.InstitutionEducationDTO;
import com.coinsystem.system.model.InstitutionEducation;

import java.util.List;

public interface IInstitutionEducation {
    InstitutionEducation register(InstitutionEducationDTO teacherDTO);

    List<InstitutionEducation> getAllStudent();

    InstitutionEducation update(Long id, InstitutionEducationDTO studentDTO);

    boolean delete(Long id);
}
