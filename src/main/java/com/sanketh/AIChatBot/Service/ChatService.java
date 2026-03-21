package com.sanketh.AIChatBot.Service;
import com.sanketh.AIChatBot.DTO.Request;
import com.sanketh.AIChatBot.DTO.Response;
import com.sanketh.AIChatBot.Entity.Prompt;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@Service
public class ChatService {
    private final UserService userService;

    private final PromptService promptService;
    private final RestClient restClient;
    private final String model;
    public ChatService(UserService userService, PromptService promptService, @Value("${ollama.base-url}") String baseUrl, @Value("${ollama.model}") String model) {
        this.userService = userService;
        this.promptService = promptService;
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
        Prompt promptEntity = new Prompt();
        if (response != null) {

            promptEntity.setPrompt(prompt);
            promptEntity.setResponse(response.response());
            promptEntity.setCreatedAt(LocalDateTime.now());
            promptService.getPrompt(promptEntity);
            UserService

            return response.response();

        } else {
            promptEntity.setPrompt(prompt);
            promptEntity.setResponse("no response given");
            promptEntity.setCreatedAt(LocalDateTime.now());
            return null;
        }
    }
}