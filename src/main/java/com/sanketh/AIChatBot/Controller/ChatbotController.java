package com.sanketh.AIChatBot.Controller;

import com.sanketh.AIChatBot.DTO.PromptResponse;
import com.sanketh.AIChatBot.DTO.Response;
import com.sanketh.AIChatBot.Exception.ChatResponseGenerationException;
import com.sanketh.AIChatBot.Exception.NothingToDeleteException;
import com.sanketh.AIChatBot.Service.ChatService;
import com.sanketh.AIChatBot.Service.UserDetailsStorage;
import com.sanketh.AIChatBot.Service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<List<PromptResponse>> getHistory() {
        List<PromptResponse> history = chatService.getHistory();
        if (!history.isEmpty()) {
            return new ResponseEntity<>(history, HttpStatus.OK);

        }
        else
           throw new NothingToDeleteException("Nothing to delete");


    }

    @GetMapping("/response")
    public ResponseEntity<Response> chat(@RequestParam("prompt") String prompt) {
        String response = chatService.getResponse(prompt);
        if (response == null) {
            return new ResponseEntity<>(new Response(response), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        else
           throw new ChatResponseGenerationException("Failed to generate response");
    }
    @PostMapping("/clearAllhistory")
    public ResponseEntity<?> clearAllHistory() {
     boolean deleted =   chatService.deleteAllHistory();
     if(deleted){
         return new ResponseEntity<>("All history cleared successfully", HttpStatus.OK);
     }
        else
           throw new NothingToDeleteException("Nothing to delete");
    }

    @GetMapping("/ping")
    public ResponseEntity<String> ping() {
        return new ResponseEntity<>("Ok", HttpStatus.OK);
    }
}