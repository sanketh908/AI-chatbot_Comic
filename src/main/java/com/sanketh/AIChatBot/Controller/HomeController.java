package com.sanketh.AIChatBot.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class HomeController {
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
    public ResponseEntity<> register() {


    }

}
