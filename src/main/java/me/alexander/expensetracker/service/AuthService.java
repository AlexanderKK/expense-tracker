package me.alexander.expensetracker.service;

import jakarta.servlet.http.HttpServletRequest;
import me.alexander.expensetracker.config.security.UserPrincipal;
import me.alexander.expensetracker.model.dto.user.LoginDTO;
import me.alexander.expensetracker.model.session.LoginResponse;
import me.alexander.expensetracker.model.session.LogoutResponse;

public interface AuthService {

    LoginResponse attemptLogin(LoginDTO loginDTO);

    LogoutResponse attemptLogout(HttpServletRequest request, UserPrincipal userPrincipal);

}
