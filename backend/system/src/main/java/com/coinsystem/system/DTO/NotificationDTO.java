package com.coinsystem.system.DTO;

import java.time.LocalDate;


public record NotificationDTO(
        String coupon,
        LocalDate dateShipping,
        LocalDate dateReceipt,
        Long idSender, // teacher
        Long idPartnerCompany) {

}
