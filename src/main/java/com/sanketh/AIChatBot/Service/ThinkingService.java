package com.sanketh.AIChatBot.Service;

import com.sanketh.AIChatBot.Entity.Prompt;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

@Component
public class ThinkingService {
 private  final     PromptService promptService;
 private  final     ChatService chatService;
 private  final     UserDetailsStorage userDetailsStorage;

    public  ThinkingService(PromptService promptService, ChatService chatService, UserDetailsStorage userDetailsStorage) {
        this.promptService = promptService;
        this.chatService = chatService;
        this.userDetailsStorage = userDetailsStorage;
    }
    public String getThinkingResponse(String prompt)
    {

        Integer id= userDetailsStorage.getCurrentUser().getId();
        List<Prompt> lst2prom= promptService.findTop2ByUserIdOrderByCreatedAtDesc(id);
        if(!lst2prom.isEmpty()) {
            HashMap<String, String> map = new HashMap<>();
            lst2prom.forEach(s -> map.put("User:"+s.getPrompt(),"Assistant:"+s.getResponse()));
            return chatService.getResponse("You are a helpful and conversational AI assistant." +
                    "Below are some previous interactions between the user and you:"+
                    map+
                    "Use this information only if it is relevant to the current prompt. Do not mention that you are using past conversations. Do not refer to them as history or context."+
                    "Respond naturally as if you already understand the user's situation. Keep the tone smooth, human-like, and continuous."+
                    "Current user prompt: "+prompt,prompt);

        }
        else
            return chatService.getResponse("You are a helpful and conversational AI assistant." +
                    "Respond naturally and clearly to the user's prompt" +
                    "User prompt:"+prompt,prompt);

    }
}
