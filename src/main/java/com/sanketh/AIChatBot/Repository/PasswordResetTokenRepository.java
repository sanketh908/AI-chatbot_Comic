package com.sanketh.AIChatBot.Repository;

import com.sanketh.AIChatBot.Entity.PasswordResetToken;
import com.sanketh.AIChatBot.Entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.Optional;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Integer> {
    Optional<PasswordResetToken> findByToken(String token);
    @Transactional
    @Modifying
    void deleteByUser(User user);
}
