package me.alexander.expensetracker.config.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomJwtDecoder {

    private final JwtProperties properties;

    @Autowired
    public CustomJwtDecoder(JwtProperties properties) {
        this.properties = properties;
    }

    public DecodedJWT decode(String token) {
        return JWT
                .require(Algorithm.HMAC256(properties.getSecretKey()))
                .build()
                .verify(token);
    }

}
