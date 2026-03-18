package com.sanketh.AIChatBot.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Response {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    private String response;
    @ManyToOne
    @JoinColumn(name = "chat_id")
    private Chat chat;
}
