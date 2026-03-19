package com.sanketh.AIChatBot.Controller;

import com.sanketh.AIChatBot.Service.ChatService;
import com.sanketh.AIChatBot.DTO.ChatRequest;
import com.sanketh.AIChatBot.DTO.ChatResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/chat")
@CrossOrigin(origins = "*")
public class ChatbotController {

    private final ChatService chatService;

    public ChatbotController(ChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping
    public ChatResponse chat(@RequestBody ChatRequest request) {
        String answer = chatService.getResponse(request.getMessage());
        return new ChatResponse(answer);
    }

    @GetMapping("/ping")
    public String ping() {
        return "ok";
    }
}