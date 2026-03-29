package com.sanketh.AIChatBot.Service;

import com.sanketh.AIChatBot.DTO.Request;
import com.sanketh.AIChatBot.Entity.Prompt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class ThinkingService {
 private  final     PromptService promptService;
 private  final     ChatService chatService;

    public  ThinkingService(PromptService promptService, ChatService chatService) {
        this.promptService = promptService;
        this.chatService = chatService;
    }
    public String getThinkingResponse(String prompt)
    {
        List<Prompt> lst2prom= promptService.findTop3ByUserIdOrderByDesc();
        if(lst2prom.size()!=0) {
            HashMap<String, String> map = new HashMap<>();
            lst2prom.stream().map(s -> map.put("User:"+s.getPrompt(),"Assistant:"+s.getResponse()));
            return chatService.getResponse("You are a helpful and conversational AI assistant." +
                    "Below are some previous interactions between the user and you:"+
                    map+
                    "Use this information only if it is relevant to the current prompt. Do not mention that you are using past conversations. Do not refer to them as history or context."+
                    "Respond naturally as if you already understand the user's situation. Keep the tone smooth, human-like, and continuous."+
                    "Current user prompt: "+prompt);
        }
        else
            return chatService.getResponse("You are a helpful and conversational AI assistant." +

                    "Respond naturally and clearly to the user's prompt" +
                    "User prompt:"+prompt);

    }
}
