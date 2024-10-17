package com.coinsystem.system.service.interfaces;

import java.util.List;

import com.coinsystem.system.DTO.VantageDTO;
import com.coinsystem.system.model.Vantage;

public interface IVantageService {

    Vantage register(VantageDTO vantageDTO);

    List<Vantage> getAllVantage();

    Vantage getVantageById(Long id);

    List<Vantage> getVantagesByPartnerCompanyId(Long partnerCompanyId);

    Vantage update(Long id, VantageDTO vantageDTO);

    boolean delete(Long id);
}
