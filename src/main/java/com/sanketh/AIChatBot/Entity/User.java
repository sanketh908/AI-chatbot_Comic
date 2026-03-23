package com.sanketh.AIChatBot.Entity;
import java.util.ArrayList;
import java.util.List;

import com.sanketh.AIChatBot.Enums.Roles;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Ai-users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false,unique = true)
    private int id;
    @Column(name = "username", nullable = false)
    private String username;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "email", nullable = false, unique = true)
    private String email;
    @Enumerated(EnumType.STRING)
    @Column(name = "roles", nullable = false)
    private Roles role;
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Prompt> prompts =new ArrayList<>();
}
