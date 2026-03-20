package com.sanketh.AIChatBot.Controller;

import com.sanketh.AIChatBot.Service.ChatService;
import com.sanketh.AIChatBot.DTO.ChatRequest;
import com.sanketh.AIChatBot.DTO.ChatResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class ChatbotController {

    private final ChatService chatService;

    public ChatbotController(ChatService chatService) {
        this.chatService = chatService;
    }

    @GetMapping("/chat")
    public String chat(@RequestParam String prompt) {
        String answer = chatService.getResponse(prompt);
        return answer;
    }

    @GetMapping("/ping")
    public String ping() {
        return "ok";
    }
}