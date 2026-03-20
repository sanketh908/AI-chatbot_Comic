package com.sanketh.AIChatBot.Controller;

import com.sanketh.AIChatBot.DTO.Response;
import com.sanketh.AIChatBot.Service.ChatService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chat")
@CrossOrigin(origins = "*")
public class ChatbotController {

    private final ChatService chatService;

    public ChatbotController(ChatService chatService) {
        this.chatService = chatService;
    }

    @GetMapping("/chat")
    public ResponseEntity<Response> chat(@RequestParam String prompt) {
        String responce= chatService.getResponse(prompt);
        if (responce.equals("No response from the model.")) {
            return new ResponseEntity<>(new Response(responce), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(new Response(responce), HttpStatus.OK);
    }

    @GetMapping("/ping")
    public ResponseEntity<String> ping() {
        return new ResponseEntity<>("Ok", HttpStatus.OK);
    }
}