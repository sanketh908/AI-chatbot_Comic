package com.sanketh.AIChatBot.Controller;

import com.sanketh.AIChatBot.DTO.Response;
import com.sanketh.AIChatBot.Entity.Prompt;
import com.sanketh.AIChatBot.Entity.User;
import com.sanketh.AIChatBot.Service.ChatService;
import com.sanketh.AIChatBot.Service.UserDetailsStorage;
import com.sanketh.AIChatBot.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.nio.file.attribute.UserPrincipal;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/chat")
@CrossOrigin(origins = "*")
public class ChatbotController {

    private final ChatService chatService;
    private final UserDetailsStorage userService;

    public ChatbotController(ChatService chatService, UserService userService, UserDetailsStorage userService1) {
        this.chatService = chatService;
        this.userService = userService1;
    }
    @GetMapping("/history")
    public ResponseEntity<List<Prompt>> getHistory() {
        User currentUser = userService.getCurrentUser();
        List<Prompt> history = currentUser.getPrompts() != null ? currentUser.getPrompts() : new ArrayList<>();
        return new ResponseEntity<>(history, HttpStatus.OK);

    }

    @GetMapping("/response")
    public ResponseEntity<Response> chat(@RequestParam("prompt") String prompt) {
        String response = chatService.getResponse(prompt);
        if (response == null) {
            return new ResponseEntity<>(new Response(response), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(new Response(response), HttpStatus.OK);
    }

    @GetMapping("/ping")
    public ResponseEntity<String> ping() {
        return new ResponseEntity<>("Ok", HttpStatus.OK);
    }
}