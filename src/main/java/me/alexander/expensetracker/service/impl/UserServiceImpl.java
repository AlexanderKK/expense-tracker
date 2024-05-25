package me.alexander.expensetracker.service.impl;

import jakarta.persistence.EntityNotFoundException;
import me.alexander.expensetracker.model.dto.user.RegisterDTO;
import me.alexander.expensetracker.model.entity.Role;
import me.alexander.expensetracker.model.entity.User;
import me.alexander.expensetracker.model.enums.RoleEnum;
import me.alexander.expensetracker.repository.RoleRepository;
import me.alexander.expensetracker.repository.UserRepository;
import me.alexander.expensetracker.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

import static me.alexander.expensetracker.constants.Messages.ENTITY_NOT_FOUND;

@Service
public class UserServiceImpl implements UserService {

    private final ModelMapper mapper;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public UserServiceImpl(ModelMapper mapper,
                           PasswordEncoder passwordEncoder,
                           UserRepository userRepository,
                           RoleRepository roleRepository) {
        this.mapper = mapper;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public void registerUser(RegisterDTO registerDTO) {
        User user = mapper.map(registerDTO, User.class);

        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        Role roleUser = roleRepository.findByRole(RoleEnum.USER)
                        .orElseThrow(() -> new EntityNotFoundException(String.format(ENTITY_NOT_FOUND, "Role")));
        user.setRoles(Set.of(roleUser));

        userRepository.save(user);
    }

}
