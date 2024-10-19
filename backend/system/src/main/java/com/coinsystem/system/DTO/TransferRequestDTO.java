package com.coinsystem.system.DTO;

public record TransferRequestDTO(
    Long sourceWalletId,
    Long destinationWalletId,
    int amount,
    String description
) {}
