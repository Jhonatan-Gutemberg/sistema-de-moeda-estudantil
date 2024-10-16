package com.coinsystem.system.DTO;

import java.time.LocalDate;


public record NotificationDTO(
        String coupon,
        LocalDate date_shipping,
        LocalDate date_receipt,
        Long id_sender, // teacher
        Long id_partner_company) {

}
