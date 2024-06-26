package me.alexander.expensetracker.config.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Component
public class JwtIssuer {

    private final JwtProperties properties;

    @Autowired
    public JwtIssuer(JwtProperties properties) {
        this.properties = properties;
    }

    public String issue(Long userId, String email, List<String> roles) {
        return JWT
                .create()
                .withSubject(String.valueOf(userId))
                .withExpiresAt(Instant.now().plus(Duration.of(1, ChronoUnit.HOURS)))
                .withClaim("e", email)
                .withClaim("a", roles)
                .sign(Algorithm.HMAC256(properties.getSecretKey()));
    }

}
