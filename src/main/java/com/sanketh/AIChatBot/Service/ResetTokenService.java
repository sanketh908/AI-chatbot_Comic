package com.sanketh.AIChatBot.Service;

import com.sanketh.AIChatBot.Entity.PasswordResetToken;
import com.sanketh.AIChatBot.Entity.User;
import com.sanketh.AIChatBot.Repository.PasswordResetTokenRepository;
import com.sanketh.AIChatBot.Repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.mail.MailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class ResetTokenService {

    private final PasswordResetTokenRepository passwordResetTokenRepository;
    private final UserRepository userRepository;
    private final MailService mailService;
     PasswordEncoder passwordEncoder=new BCryptPasswordEncoder(12);

    public ResetTokenService(PasswordResetTokenRepository passwordResetTokenRepository, UserRepository userRepository, MailService mailService) {
        this.passwordResetTokenRepository = passwordResetTokenRepository;
        this.userRepository = userRepository;
        this.mailService = mailService;
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
    public void resetPassword(String token,String newPassword) {
        PasswordResetToken resetToken = validateToken(token);
        User user = resetToken.getUser();
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
        passwordResetTokenRepository.delete(resetToken);


    }
    public void sendMail(User user)
    {
        String token =createResetToken(user);
        String link="http://localhost:8080/AIChatBot/reset-token?token="+token;
        mailService.sendmail(user.getEmail(),"Password Reset Request","Click the link to reset your password: "+link);


    }
}
