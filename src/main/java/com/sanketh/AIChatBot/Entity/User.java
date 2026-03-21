package com.sanketh.AIChatBot.Entity;
import java.util.List;
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
    private String role;
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Chat> prompts;
}
