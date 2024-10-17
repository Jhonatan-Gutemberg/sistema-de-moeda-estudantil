package com.coinsystem.system.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coinsystem.system.DTO.VantageDTO;
import com.coinsystem.system.exception.UserNotFoundException;
import com.coinsystem.system.mappers.VantageMapper;
import com.coinsystem.system.model.PartnerCompany;
import com.coinsystem.system.model.Vantage;
import com.coinsystem.system.repository.PartnerCompanyRepository;
import com.coinsystem.system.repository.VantageRepository;
import com.coinsystem.system.service.interfaces.IVantageService;

@Service
public class VantageService implements IVantageService {

    @Autowired
    private VantageRepository vantageRepository;

    @Autowired
    private PartnerCompanyRepository partnerCompanyRepository;

    @Override
    public Vantage register(VantageDTO vantageDTO) {

        PartnerCompany partnerCompany = partnerCompanyRepository.findById(vantageDTO.id_partnerCompany())
                .orElseThrow(() -> new UserNotFoundException("User with id  not found."));
        ;

        Vantage vantage = VantageMapper.vantageDtoToModel(vantageDTO);

        vantage.setPartnerCompany(partnerCompany);

        vantageRepository.save(vantage);
        return vantage;
    }

    @Override
    public List<Vantage> getAllVantage() {
        return vantageRepository.findAll();
    }

    @Override
    public Vantage getVantageById(Long id) {
        Optional<Vantage> vantage = vantageRepository.findById(id);

        return vantage.orElseThrow(() -> new UserNotFoundException("Vantage with id " + id + " not found."));

    }

    @Override
    public List<Vantage> getVantagesByPartnerCompanyId(Long partnerCompanyId) {
        PartnerCompany partnerCompany = partnerCompanyRepository.findById(partnerCompanyId)
                .orElseThrow(
                        () -> new UserNotFoundException("Partner company with id " + partnerCompanyId + " not found."));

        return vantageRepository.findByPartnerCompany(partnerCompany);
    }

    @Override
    public Vantage update(Long id, VantageDTO vantageDTO) {
        Optional<Vantage> optional = vantageRepository.findById(id);
        if (optional.isPresent()) {
            Vantage existingVantage = optional.get();
            existingVantage.setName(vantageDTO.name());
            existingVantage.setDescription(vantageDTO.description());
            existingVantage.setValue(vantageDTO.value());
            ;

            vantageRepository.save(existingVantage);
            return existingVantage;
        } else {
            throw new UserNotFoundException("Vantage with id " + id + " not found.");
        }
    }

    @Override
    public boolean delete(Long id) {
        Optional<Vantage> optional = vantageRepository.findById(id);
        if (optional.isPresent()) {
            vantageRepository.delete(optional.get());
            return true;
        }

        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

}
