package com.coinsystem.system.DTO;

import java.time.LocalDateTime;

public record ExchangeDTO(
        Long id,
        Long studentId,
        Long vantageId,
        LocalDateTime date,
        int coinsSpent) {
}
