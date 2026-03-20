package com.sanketh.AIChatBot.Service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class ChatService {
    private final RestClient restClient;
    private final String model;
    public ChatService(@Value("${ollama.base-url}") String baseUrl, @Value("${ollama.model}") String model) {
        this.restClient = RestClient.builder().baseUrl(baseUrl).build();
        this.model = model;
    }
}