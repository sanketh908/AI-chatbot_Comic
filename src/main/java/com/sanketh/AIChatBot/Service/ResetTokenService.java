package com.sanketh.AIChatBot.Service;

import com.sanketh.AIChatBot.Entity.PasswordResetToken;
import com.sanketh.AIChatBot.Entity.User;
import com.sanketh.AIChatBot.Repository.PasswordResetTokenRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class ResetTokenService {
    private final PasswordResetTokenRepository passwordResetTokenRepository;

    public ResetTokenService(PasswordResetTokenRepository passwordResetTokenRepository) {
        this.passwordResetTokenRepository = passwordResetTokenRepository;
    }

    public String createResetToken(User user) {
        passwordResetTokenRepository.deleteByUser(user);
        String token = UUID.randomUUID().toString();
        PasswordResetToken passwordResetToken = new PasswordResetToken();
        passwordResetToken.setToken(token);
        passwordResetToken.setUser(user);
        passwordResetToken.setExpiryDate(LocalDateTime.now().plusMinutes(5));
        passwordResetTokenRepository.save(passwordResetToken);
        return token;
    }
    public PasswordResetToken validateToken(String token) {
        PasswordResetToken passwordResetToken =passwordResetTokenRepository.findByToken(token).orElseThrow(()->new RuntimeException("Invalid token"));
        if(passwordResetToken.getExpiryDate().isBefore(LocalDateTime.now())) {
            throw new RuntimeException(" token  is expired");
        }
        return passwordResetToken;

    }
}
