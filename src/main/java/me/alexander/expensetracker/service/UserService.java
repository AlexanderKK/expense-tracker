package me.alexander.expensetracker.service;

import me.alexander.expensetracker.model.dto.user.RegisterDTO;

public interface UserService {

    void registerUser(RegisterDTO registerDTO);

}
