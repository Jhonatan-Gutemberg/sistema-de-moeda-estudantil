package com.coinsystem.system.service.interfaces;

import java.util.List;

import com.coinsystem.system.model.Wallet;

public interface IWalletService {
    boolean transferCoins(Long sourceWalletId, Long destinationWalletId, int amount, String description);
     List<Wallet> getAllWallet();
}
