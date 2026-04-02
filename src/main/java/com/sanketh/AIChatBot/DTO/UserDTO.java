package com.sanketh.AIChatBot.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    @Email(message = "Invalid email format")
    @NotBlank(message = "email cant be blank")
    String email;
    @NotBlank(message = "Password cannot be blank")
    String password;
    String username;
}
