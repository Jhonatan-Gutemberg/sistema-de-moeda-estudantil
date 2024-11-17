package com.coinsystem.system.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.coinsystem.system.DTO.InstitutionEducationDTO;
import com.coinsystem.system.exception.UserNotFoundException;
import com.coinsystem.system.mappers.UsersMapper;
import com.coinsystem.system.model.InstitutionEducation;
import com.coinsystem.system.repository.InstitutionEducationRepository;
import com.coinsystem.system.service.interfaces.IInstitutionEducationService;

@Service
public class InstitutionEducationalService implements IInstitutionEducationService {

    @Autowired
    private InstitutionEducationRepository institutionEducationRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public InstitutionEducation register(InstitutionEducationDTO institutionEducationDTO) {
        InstitutionEducation institutionEducation = UsersMapper.InstitutionEducationDtoToModel(institutionEducationDTO);
        institutionEducation.setPassword(passwordEncoder.encode(institutionEducationDTO.password()));
        institutionEducationRepository.save(institutionEducation);
        return institutionEducation;
    }

    @Override
    public List<InstitutionEducation> getAllInstitutionEducation() {
        return institutionEducationRepository.findAll();
    }

    @Override
    public InstitutionEducation getInstitutionEducationById(Long id) {
        Optional<InstitutionEducation> InstitutionEducation = institutionEducationRepository.findById(id);

        return InstitutionEducation.orElseThrow(() -> new UserNotFoundException("User with id " + id + " not found."));
    }

    @Override
    public InstitutionEducation update(Long id, InstitutionEducationDTO institutionEducationDTO) {
        Optional<InstitutionEducation> optional = institutionEducationRepository.findById(id);
        if (optional.isPresent()) {
            InstitutionEducation existingInstitutionEducation = optional.get();
            existingInstitutionEducation.setAddress(institutionEducationDTO.address());
            existingInstitutionEducation.setPhoneNumber(institutionEducationDTO.phone_number());
            existingInstitutionEducation.setPassword(institutionEducationDTO.password());

            institutionEducationRepository.save(existingInstitutionEducation);
            return existingInstitutionEducation;
        } else {
            throw new UserNotFoundException("User with id " + id + " not found.");
        }
    }

    @Override
    public boolean delete(Long id) {
        Optional<InstitutionEducation> optional = institutionEducationRepository.findById(id);
        if (optional.isPresent()) {
            institutionEducationRepository.delete(optional.get());
            return true;
        }

        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

}
