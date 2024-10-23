package com.coinsystem.system.DTO;

public record EmailDTO(
    String from,
    String to,
    String subject,
    String body
) {
    
}
