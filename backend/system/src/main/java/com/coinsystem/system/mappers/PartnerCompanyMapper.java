package com.coinsystem.system.mappers;

import com.coinsystem.system.DTO.PartnerCompanyDTO;
import com.coinsystem.system.model.PartnerCompany;

public class PartnerCompanyMapper {
    public static PartnerCompanyDTO partnerCompanyToPartnerCompanyDTO(PartnerCompany partnerCompany) {
        return new PartnerCompanyDTO(
                partnerCompany.getName(),
                partnerCompany.getEmail(),
                partnerCompany.getType(),
                partnerCompany.getPhoneNumber(),
                partnerCompany.getCnpj(),
                partnerCompany.getPassword(),
                partnerCompany.getAddress()
        );
    }
}
