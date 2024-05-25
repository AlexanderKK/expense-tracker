package me.alexander.expensetracker.config.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import me.alexander.expensetracker.repository.TokenRepository;
import me.alexander.expensetracker.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtDecoder jwtDecoder;
    private final JwtToPrincipalConverter jwtToPrincipalConverter;
    private final TokenRepository tokenRepository;

    @Autowired
    public JwtAuthenticationFilter(JwtDecoder jwtDecoder, JwtToPrincipalConverter jwtToPrincipalConverter, TokenRepository tokenRepository) {
        this.jwtDecoder = jwtDecoder;
        this.jwtToPrincipalConverter = jwtToPrincipalConverter;
        this.tokenRepository = tokenRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        extractTokenFromRequest(request)
                .map(jwtDecoder::decode)
                .map(jwtToPrincipalConverter::convert)
                .map(UserPrincipalAuthenticationToken::new)
                .ifPresent(authentication -> SecurityContextHolder.getContext().setAuthentication(authentication));

        filterChain.doFilter(request, response);
    }

    private Optional<String> extractTokenFromRequest(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");

        boolean isNullOrEmpty = StringUtils.isNullOrEmpty(authHeader);
        boolean startsWithBearer = !isNullOrEmpty && authHeader.startsWith("Bearer ");

        String token = isNullOrEmpty || !startsWithBearer ? authHeader : authHeader.substring(7);

        if(isNullOrEmpty || !startsWithBearer || !this.isTokenValid(token)) {
            return Optional.empty();
        }

        return Optional.of(token);
    }

    private boolean isTokenValid(String token) {
        return tokenRepository.findByToken(token)
                .map(t -> !t.isLoggedOut())
                .orElse(false);
    }

}
