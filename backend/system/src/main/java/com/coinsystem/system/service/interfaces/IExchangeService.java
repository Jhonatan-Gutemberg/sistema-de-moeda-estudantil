package com.coinsystem.system.service.interfaces;

import java.util.List;

import com.coinsystem.system.DTO.ExchangeDTO;

public interface IExchangeService {
    ExchangeDTO exchangeVantage(Long studentId, Long vantageId);
    List<ExchangeDTO> getExchangeHistory(Long studentId);

    void confirmStudentReceipt(Long notificationId);
    void confirmPartnerReceipt(Long notificationId);

}
