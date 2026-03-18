package com.sanketh.AIChatBot.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(unique = true)
    private String userRequest;
    @OneToMany(cascade = CascadeType.ALL)
    List<Response> responses;
    @Column(name = "timestamp")
    LocalDateTime timestamp ;
}
