package me.alexander.expensetracker.config.security;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import me.alexander.expensetracker.model.entity.Token;
import me.alexander.expensetracker.repository.TokenRepository;
import me.alexander.expensetracker.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

@Component
public class CustomLogoutHandler implements LogoutHandler {

    private final TokenRepository tokenRepository;

    @Autowired
    public CustomLogoutHandler(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        String authHeader = request.getHeader("Authorization");

        if(StringUtils.isNullOrEmpty(authHeader) || !authHeader.startsWith("Bearer ")) {
            throw new IllegalStateException("Invalid bearer token");
        }

        String token = authHeader.substring(7);
        Token existingToken = tokenRepository.findByToken(token).orElse(null);

        if(existingToken != null) {
            if(existingToken.isLoggedOut()) {
                throw new IllegalStateException("Token already revoked");
            }

            existingToken.setLoggedOut(true);
            tokenRepository.save(existingToken);
        }
    }

}
