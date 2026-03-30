package com.sanketh.AIChatBot.Entity;

import com.sanketh.AIChatBot.Enums.Roles;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class PasswordResetToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String token;
    @OneToOne()
    private User user;
    private LocalDateTime expiryDate;
}
