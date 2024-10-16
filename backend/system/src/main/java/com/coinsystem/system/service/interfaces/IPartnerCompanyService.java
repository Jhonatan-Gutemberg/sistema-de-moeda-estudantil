package com.coinsystem.system.service.interfaces;

import com.coinsystem.system.DTO.PartnerCompanyDTO;
import com.coinsystem.system.model.PartnerCompany;

import java.util.List;

public interface IPartnerCompanyService {
    PartnerCompany register(PartnerCompanyDTO partnerCompanyDTO);

    List<PartnerCompany> getAllPartnerCompany();

    PartnerCompany getPartnerCompanyById(Long id);

    PartnerCompany update(Long id, PartnerCompanyDTO partnerCompanyDTO);

    boolean delete(Long id);
}
