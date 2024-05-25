package me.alexander.expensetracker.repository;

import me.alexander.expensetracker.model.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {

    Set<Token> findAllByUserIdAndLoggedOutIsFalse(Long userId);

    Optional<Token> findByToken(String token);

}
