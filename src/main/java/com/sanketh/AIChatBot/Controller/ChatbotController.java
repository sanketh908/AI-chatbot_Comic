package com.sanketh.AIChatBot.Controller;

import com.sanketh.AIChatBot.DTO.PromptResponse;
import com.sanketh.AIChatBot.DTO.Response;
import com.sanketh.AIChatBot.Exception.ChatResponseGenerationException;
import com.sanketh.AIChatBot.Exception.NothingToDeleteException;
import com.sanketh.AIChatBot.Service.ChatService;
import com.sanketh.AIChatBot.Service.ThinkingService;
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
    private final ThinkingService thinkingService;


    public ChatbotController(ChatService chatService, ThinkingService thinkingService) {
        this.chatService = chatService;

        this.thinkingService = thinkingService;
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

    @GetMapping("/response/stateless")
    public ResponseEntity<Response> chat(@RequestParam("prompt") String prompt) {
        String response = chatService.getResponse(prompt);
        if (response != null) {
            return new ResponseEntity<>(new Response(response), HttpStatus.OK);
        }
        else
           throw new ChatResponseGenerationException("Failed to generate response");
    }
    @GetMapping("/response/statefull")
    public ResponseEntity<Response> thinkingMode(@RequestParam("prompt") String prompt) {
        String response = thinkingService.getThinkingResponse(prompt);
        if (response != null) {
            return new ResponseEntity<>(new Response(response), HttpStatus.OK);
        }
        else {
            throw new ChatResponseGenerationException("Failed to generate response");
        }
    }
    @PostMapping("/deletehistory/{id}")
    public ResponseEntity<String> deleteHistory(@PathVariable("id") Integer id) {
        chatService.deleteHistoryBytId(id);
        return new ResponseEntity<>("deleted succesfully", HttpStatus.OK);

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