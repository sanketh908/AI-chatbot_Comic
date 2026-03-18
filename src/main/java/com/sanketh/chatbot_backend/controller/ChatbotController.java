package com.sanketh.chatbot_backend.controller;

import com.sanketh.chatbot_backend.dto.ChatRequest;
import com.sanketh.chatbot_backend.dto.ChatResponse;
import com.sanketh.chatbot_backend.dto.Message;
import com.sanketh.chatbot_backend.service.ChatbotService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/chat")
@CrossOrigin(origins = "${chatbot.allowed-origins:http://localhost:3000}")
public class ChatbotController {

    private final ChatbotService chatbotService;

    public ChatbotController(ChatbotService chatbotService) {
        this.chatbotService = chatbotService;
    }

    @PostMapping
    public ResponseEntity<?> chat(@RequestBody ChatRequest request) {
        if (request.getMessage() == null || request.getMessage().trim().isEmpty()) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error", "Message cannot be null or empty"));
        }
        String response = chatbotService.chat(request.getMessage());
        return ResponseEntity.ok(new ChatResponse(response, LocalDateTime.now()));
    }

    @GetMapping("/history")
    public ResponseEntity<List<Message>> getHistory() {
        return ResponseEntity.ok(chatbotService.getHistory());
    }

    @PostMapping("/clear")
    public ResponseEntity<Void> clearHistory() {
        chatbotService.clearHistory();
        return ResponseEntity.ok().build();
    }
}
