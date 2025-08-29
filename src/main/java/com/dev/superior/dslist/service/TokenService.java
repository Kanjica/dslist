package com.dev.superior.dslist.service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.dev.superior.dslist.users.User;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String generateToken(User user) {
        Algorithm algorithm = Algorithm.HMAC256(secret);

        return JWT.create()
            .withSubject(user.getUsername())
            .withExpiresAt(Date.from(Instant.now().plus(2, ChronoUnit.HOURS)))
            .withIssuer("dslist-api")
            .sign(algorithm);
    }

    public String validateToken(String token){
        Algorithm algorithm = Algorithm.HMAC256(secret);
        return JWT.require(algorithm)
                    .withIssuer("dslist-api")
                    .build()
                    .verify(token)
                    .getSubject();
    }

}
