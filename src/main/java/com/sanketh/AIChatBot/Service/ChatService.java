package com.sanketh.AIChatBot.Service;

import com.sanketh.AIChatBot.Entity.Response;
import com.sanketh.AIChatBot.Repository.ChatRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ChatService {
 private final ChatRepository chatRepository;

    public ChatService(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }
    @Value("${gemini.api.url}")
    private String url;
    @Value("${gemini.api.key}")
    private String key;
    String finalkey=url+key;
    public Response getAiResponse(String request) {
        RestTemplate restTemplate = new RestTemplate();
        Response response = restTemplate.exchange(finalkey, HttpMethod.POST, null, Response.class).getBody();
        chatRepository.save(response);
        return response;
    }
    }

