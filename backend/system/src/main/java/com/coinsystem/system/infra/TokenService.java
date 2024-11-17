package com.coinsystem.system.infra;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.coinsystem.system.model.Users;

@Service
public class TokenService implements ITokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String generateToken(Users systemUser) {

        try {

            System.out.println("Secret: " + secret);
            Algorithm algorithm = Algorithm.HMAC256(secret);

            String token = JWT.create()
                    .withIssuer("login-auth-api")
                    .withSubject(systemUser.getEmail())
                    .withExpiresAt(this.generationExpirationDate())
                    .sign(algorithm);

            return token;

        } catch (JWTCreationException exception) {
            throw new RuntimeException("Error while authenticating!");
        }

    }

    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            DecodedJWT decodedJWT = JWT.require(algorithm)
                    .withIssuer("login-auth-api")
                    .build()
                    .verify(token);

            System.out.println("Token válido: " + decodedJWT.getSubject());
            return decodedJWT.getSubject();
        } catch (JWTVerificationException e) {
            System.out.println("Erro de verificação do token: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    private Instant generationExpirationDate() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

}
