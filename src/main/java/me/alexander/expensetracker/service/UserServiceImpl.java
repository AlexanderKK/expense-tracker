package me.alexander.expensetracker.service;

import me.alexander.expensetracker.model.dto.user.RegisterDTO;
import me.alexander.expensetracker.model.entity.User;
import me.alexander.expensetracker.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final ModelMapper mapper;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(ModelMapper mapper,
                           PasswordEncoder passwordEncoder,
                           UserRepository userRepository) {
        this.mapper = mapper;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Override
    public void registerUser(RegisterDTO registerDTO) {
        User user = mapper.map(registerDTO, User.class);

        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        userRepository.save(user);
    }

}
