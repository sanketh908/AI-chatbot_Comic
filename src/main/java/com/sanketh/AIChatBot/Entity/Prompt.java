package com.sanketh.AIChatBot.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@Entity
@Table(name = "prompts")
public class Prompt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "p_id", nullable = false,unique = true)
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
