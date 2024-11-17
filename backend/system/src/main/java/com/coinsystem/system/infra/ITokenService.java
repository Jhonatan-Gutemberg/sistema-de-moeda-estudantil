package com.coinsystem.system.infra;

import com.coinsystem.system.model.Users;

public interface ITokenService {

    String generateToken(Users users);
    String validateToken(String token);
    
}
