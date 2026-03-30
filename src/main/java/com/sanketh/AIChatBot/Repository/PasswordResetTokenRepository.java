package com.sanketh.AIChatBot.Repository;

import com.sanketh.AIChatBot.Entity.PasswordResetToken;
import com.sanketh.AIChatBot.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Integer> {
    Optional<PasswordResetToken> findByToken(String token);
    void deleteByUser(User user);
}
