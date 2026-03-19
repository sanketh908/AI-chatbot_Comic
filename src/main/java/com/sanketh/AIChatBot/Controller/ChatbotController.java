package com.sanketh.AIChatBot.Controller;

import com.sanketh.AIChatBot.Service.ChatService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("ai/")
public class ChatbotController {
    private final ChatService chatService;

    public ChatbotController(ChatService chatService) {
        this.chatService = chatService;
    }
    @GetMapping("/chat")
    public String getChatResponse(@RequestParam String prompt) {
        return chatService.getResponse(prompt);
    }
}
