package me.alexander.expensetracker.repository;

import me.alexander.expensetracker.model.entity.Role;
import me.alexander.expensetracker.model.enums.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByRole(RoleEnum roleEnum);

}
