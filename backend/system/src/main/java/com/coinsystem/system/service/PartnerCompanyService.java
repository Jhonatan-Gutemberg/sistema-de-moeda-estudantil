package com.coinsystem.system.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.coinsystem.system.DTO.PartnerCompanyDTO;
import com.coinsystem.system.exception.UserNotFoundException;
import com.coinsystem.system.mappers.UsersMapper;
import com.coinsystem.system.model.PartnerCompany;
import com.coinsystem.system.repository.PartnerCompanyRepository;
import com.coinsystem.system.service.interfaces.IPartnerCompanyService;
import org.springframework.stereotype.Service;

@Service
public class PartnerCompanyService implements IPartnerCompanyService {

    @Autowired
    private PartnerCompanyRepository partnerCompanyRepository;

    @Override
    public PartnerCompany register(PartnerCompanyDTO partnerCompanyDTO) {
        PartnerCompany partnerCompany = UsersMapper.PartnerCompanyDtoToModel(partnerCompanyDTO);
        partnerCompanyRepository.save(partnerCompany);
        return partnerCompany;
    }

    @Override
    public List<PartnerCompany> getAllPartnerCompany() {
        return partnerCompanyRepository.findAll();
    }

    @Override
    public PartnerCompany getPartnerCompanyById(Long id) {
        Optional<PartnerCompany> partnerCompany = partnerCompanyRepository.findById(id);

        return partnerCompany.orElseThrow(() -> new UserNotFoundException("User with id " + id + " not found."));
    }

    @Override
    public PartnerCompany update(Long id, PartnerCompanyDTO partnerCompanyDTO) {
        Optional<PartnerCompany> optional = partnerCompanyRepository.findById(id);
        if (optional.isPresent()) {
            PartnerCompany existingPartnerCompany = optional.get();
            existingPartnerCompany.setAddress(partnerCompanyDTO.address());
            existingPartnerCompany.setPhoneNumber(partnerCompanyDTO.phone_number());
            existingPartnerCompany.setPassword(partnerCompanyDTO.password());

            partnerCompanyRepository.save(existingPartnerCompany);
            return existingPartnerCompany;
        } else {
            throw new UserNotFoundException("User with id " + id + " not found.");
        }
    }

    @Override
    public boolean delete(Long id) {
        Optional<PartnerCompany> optional = partnerCompanyRepository.findById(id);
        if (optional.isPresent()) {
            partnerCompanyRepository.delete(optional.get());
            return true;
        }

        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

}
