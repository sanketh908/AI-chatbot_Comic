package com.sanketh.AIChatBot.Service;
import com.sanketh.AIChatBot.DTO.Request;
import com.sanketh.AIChatBot.DTO.Response;
import com.sanketh.AIChatBot.Entity.Prompt;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.time.LocalDateTime;

@Service
public class ChatService {
    private final RestClient restClient;
    private final String model;
    public ChatService(@Value("${ollama.base-url}") String baseUrl, @Value("${ollama.model}") String model) {
        this.restClient = RestClient.builder().baseUrl(baseUrl).build();
        this.model = model;
    }
    public String getResponse(String prompt)
    {
        Request request= new Request(model, prompt, false);
        Response response=restClient.post().uri("/api/generate")
                .body(request)
                .retrieve()
                .body(Response.class);
        if (response != null) {
            Prompt promptEntity = new Prompt();
            promptEntity.setPrompt(prompt);
            promptEntity.setResponse(response.response());
            promptEntity.setCreatedAt(LocalDateTime.now());
            return response.response();

        } else {
            return null;
        }
    }
}