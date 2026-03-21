package com.sanketh.AIChatBot.Entity;
import java.util.List;

import com.sanketh.AIChatBot.Enums.Roles;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private String password;
    private String email;
    @Enumerated(EnumType.STRING)
    private Roles role;
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Prompt> prompts;
}
