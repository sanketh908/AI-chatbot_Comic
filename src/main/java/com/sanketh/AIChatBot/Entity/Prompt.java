package com.sanketh.AIChatBot.Entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "prompts")
public class Prompt { @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
   private int id;
   private String prompt;
   private String response;
   private LocalDateTime createdAt;
}
