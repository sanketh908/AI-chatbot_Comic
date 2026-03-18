package com.sanketh.AIChatBot.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(unique = true)
    private String userMessage;
    @Column(unique = true, length = 5000)
    private String botResponse;
    @Column(name = "timestamp")
    LocalDateTime timestamp ;
}
