package me.alexander.expensetracker.service.impl;

import me.alexander.expensetracker.model.entity.Role;
import me.alexander.expensetracker.model.enums.RoleEnum;
import me.alexander.expensetracker.repository.RoleRepository;
import me.alexander.expensetracker.service.SeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

import static me.alexander.expensetracker.constants.Messages.ENTITIES_ALREADY_SEEDED;
import static me.alexander.expensetracker.constants.Messages.ENTITIES_SEEDED_SUCCESSFULLY;

@Service
public class SeedServiceImpl implements SeedService {

    private static final String ROLES = "Roles";
    private final RoleRepository roleRepository;

    @Autowired
    public SeedServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public String seedRoles() {
        if(roleRepository.count() > 0) {
            return String.format(ENTITIES_ALREADY_SEEDED, ROLES);
        }

        Arrays.stream(RoleEnum.values())
                .forEach(roleEnum -> roleRepository.save(new Role(roleEnum)));

        return String.format(ENTITIES_SEEDED_SUCCESSFULLY, ROLES);
    }

}
