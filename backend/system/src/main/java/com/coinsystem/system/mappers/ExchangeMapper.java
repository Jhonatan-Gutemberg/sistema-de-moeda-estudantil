package com.coinsystem.system.mappers;

import com.coinsystem.system.DTO.ExchangeDTO;
import com.coinsystem.system.model.Exchange;
import com.coinsystem.system.model.Student;
import com.coinsystem.system.model.Vantage;

public class ExchangeMapper {

    public static ExchangeDTO toDTO(Exchange exchange) {
        return new ExchangeDTO(
                exchange.getId(),
                exchange.getStudent().getId(),
                exchange.getVantage().getId(),
                exchange.getDate(),
                exchange.getCoinsSpent());
    }

    public static Exchange toEntity(ExchangeDTO exchangeDTO, Student student, Vantage vantage) {
        Exchange exchange = new Exchange();
        exchange.setId(exchangeDTO.id());
        exchange.setStudent(student);
        exchange.setVantage(vantage);
        exchange.setDate(exchangeDTO.date());
        exchange.setCoinsSpent(exchangeDTO.coinsSpent());
        return exchange;
    }
}
