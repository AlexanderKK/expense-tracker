package me.alexander.expensetracker.service.impl;

import jakarta.persistence.EntityNotFoundException;
import me.alexander.expensetracker.config.security.JwtIssuer;
import me.alexander.expensetracker.config.security.UserPrincipal;
import me.alexander.expensetracker.model.dto.user.LoginDTO;
import me.alexander.expensetracker.model.entity.Token;
import me.alexander.expensetracker.model.entity.User;
import me.alexander.expensetracker.model.session.LoginResponse;
import me.alexander.expensetracker.repository.TokenRepository;
import me.alexander.expensetracker.repository.UserRepository;
import me.alexander.expensetracker.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

import static me.alexander.expensetracker.constants.Messages.ENTITY_NOT_FOUND;

@Service
public class AuthServiceImpl implements AuthService {

    private final JwtIssuer jwtIssuer;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;

    @Autowired
    public AuthServiceImpl(JwtIssuer jwtIssuer,
                           AuthenticationManager authenticationManager,
                           UserRepository userRepository,
                           TokenRepository tokenRepository) {
        this.jwtIssuer = jwtIssuer;
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
    }

    @Override
    public LoginResponse attemptLogin(LoginDTO loginDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();

        List<String> roles = principal.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        String jwtToken = jwtIssuer.issue(principal.getUserId(), principal.getEmail(), roles);

        this.saveUserToken(principal, jwtToken);

        return new LoginResponse(jwtToken);
    }

    private void saveUserToken(UserPrincipal principal, String jwtToken) {
        User user = userRepository.findByEmail(principal.getEmail())
                .orElseThrow(() -> new EntityNotFoundException(String.format(ENTITY_NOT_FOUND, "User")));

        this.revokeAllTokensByUser(user);

        Token token = new Token(jwtToken, false, user);

        tokenRepository.save(token);
    }

    private void revokeAllTokensByUser(User user) {
        Set<Token> validTokensByUser = tokenRepository.findAllByUserIdAndLoggedOutIsFalse(user.getId());

        if(!validTokensByUser.isEmpty()) {
            validTokensByUser.forEach(token -> token.setLoggedOut(true));
        }

        tokenRepository.saveAll(validTokensByUser);
    }

}
