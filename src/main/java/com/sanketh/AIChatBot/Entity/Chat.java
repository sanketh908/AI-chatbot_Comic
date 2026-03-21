package com.sanketh.AIChatBot.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@Entity
@Table(name = "Chats")
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "c_id", nullable = false,unique = true)
   private int id;
    @Column(name = "prompt")
   private String prompt;
    @Column(name = "response",length = 5000)
   private String response;
    @Column(name = "created_at", nullable = false)
   private LocalDateTime createdAt;

   @ManyToOne
   @JoinColumn(name = "user_id")
    private User user;
}
