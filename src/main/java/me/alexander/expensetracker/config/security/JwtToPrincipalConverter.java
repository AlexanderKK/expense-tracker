package me.alexander.expensetracker.config.security;

import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JwtToPrincipalConverter {

    public UserPrincipal convert(DecodedJWT jwt) {
        return new UserPrincipal()
                .setUserId(Long.parseLong(jwt.getSubject()))
                .setEmail(jwt.getClaim("e").asString())
                .setAuthorities(extractAuthorities(jwt));
    }

    private List<SimpleGrantedAuthority> extractAuthorities(DecodedJWT jwt) {
        Claim claim = jwt.getClaim("a");

        if(claim.isNull() || claim.isMissing()) {
            return List.of();
        }

        return claim.asList(SimpleGrantedAuthority.class);
    }

}
