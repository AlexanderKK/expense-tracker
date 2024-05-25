package me.alexander.expensetracker.web.rest;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import me.alexander.expensetracker.config.security.UserPrincipal;
import me.alexander.expensetracker.model.dto.user.LoginDTO;
import me.alexander.expensetracker.model.session.LoginResponse;
import me.alexander.expensetracker.model.session.LogoutResponse;
import me.alexander.expensetracker.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthRestController {

    private final AuthService authService;

    @Autowired
    public AuthRestController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public LoginResponse login(@Valid @RequestBody LoginDTO loginDTO) {
        return authService.attemptLogin(loginDTO);
    }

    @GetMapping("/current-user")
    public String getCurrentUser(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        return String.format("Logged in user with email %s", userPrincipal.getEmail());
    }

    @PostMapping("/logout")
    public LogoutResponse logout(HttpServletRequest request, @AuthenticationPrincipal UserPrincipal userPrincipal) {
        return authService.attemptLogout(request, userPrincipal);
    }

}
