package com.coinsystem.system.mappers;

import com.coinsystem.system.DTO.WalletDTO;
import com.coinsystem.system.model.Wallet;

public class WalletMapper {
    public static WalletDTO walletToWalletDTO(Wallet wallet) {
        return new WalletDTO(
                wallet.getCoins(),
                wallet.getDescription()
        );
    }
}
