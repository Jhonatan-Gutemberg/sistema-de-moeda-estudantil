package com.coinsystem.system.service.interfaces;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.coinsystem.system.DTO.VantageDTO;
import com.coinsystem.system.model.Vantage;

public interface IVantageService {

    Vantage register(VantageDTO vantageDTO,  MultipartFile file) throws IOException;

    List<Vantage> getAllVantage();

    Vantage getVantageById(Long id);

    List<Vantage> getVantagesByPartnerCompanyId(Long partnerCompanyId);

    Vantage update(Long id, VantageDTO vantageDTO);

    boolean delete(Long id);
}
