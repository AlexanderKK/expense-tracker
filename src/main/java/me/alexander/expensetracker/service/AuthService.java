package me.alexander.expensetracker.service;

import me.alexander.expensetracker.config.security.UserPrincipal;
import me.alexander.expensetracker.model.dto.user.LoginDTO;
import me.alexander.expensetracker.model.session.LoginResponse;

public interface AuthService {

    LoginResponse attemptLogin(LoginDTO loginDTO);

}
