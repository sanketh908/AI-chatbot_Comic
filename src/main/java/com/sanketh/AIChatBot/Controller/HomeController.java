package com.sanketh.AIChatBot.Controller;

import com.sanketh.AIChatBot.Entity.User;
import com.sanketh.AIChatBot.Enums.Roles;
import com.sanketh.AIChatBot.Service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/home")
public class HomeController {

    private final UserService userService;

    public HomeController( UserService userService) {

        this.userService = userService;
    }

    @GetMapping("/")
    public String home() {
        return "Welcome to the AI Chatbot !";
    }
    @GetMapping("/about")
    public String about() {
        return "this is a simple AI chatbot built using Spring Boot and " +
                "Ollama local module. It can respond to user queries and provide information " +
                "on various topics.try it our ";
    }
    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody  User user) {
        user.setRole(Roles.ROLE_USER);
        User newuser=userService.getUser(user);
        if(newuser !=null){
            return new ResponseEntity<>(newuser, HttpStatus.OK);
        }else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }

}
