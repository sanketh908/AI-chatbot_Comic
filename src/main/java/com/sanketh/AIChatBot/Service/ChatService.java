package com.sanketh.AIChatBot.Service;
import com.sanketh.AIChatBot.DTO.PromptResponse;
import com.sanketh.AIChatBot.DTO.Request;
import com.sanketh.AIChatBot.DTO.Response;
import com.sanketh.AIChatBot.Entity.Prompt;
import com.sanketh.AIChatBot.Entity.User;
import com.sanketh.AIChatBot.Repository.PromptRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Slf4j
@Service
public class ChatService {

    private final UserDetailsStorage userDetailsStorage;
    private final PromptService promptService;
    private final RestClient restClient;
    private final String model;
    public ChatService(UserDetailsStorage userDetailsStorage, PromptRepository promptRepository, PromptService promptService, @Value("${ollama.base-url}") String baseUrl, @Value("${ollama.model}") String model) {
        this.userDetailsStorage = userDetailsStorage;
        this.promptService = promptService;
        this.restClient = RestClient.builder().baseUrl(baseUrl).build();
        this.model = model;
    }
    public List<Prompt> deleteAllHistory() {
     User currentUser = userDetailsStorage.getCurrentUser();
     Integer userId = currentUser.getId();
     promptService.deleteAllPrompts(userId);
    }
    public List<PromptResponse> getHistory() {
        User currentUser = userDetailsStorage.getCurrentUser();
        List<Prompt> history = promptService.findByUser(currentUser);
        log.info("Retrieved {} prompts for user {}", history.size(), currentUser.getUsername());
        return history.stream()
                .map(prompt -> new PromptResponse(prompt.getPrompt(),
                        prompt.getResponse(),
                        prompt.getCreatedAt()))
                .toList();

    }

    @Transactional
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
            promptEntity.setUser(userDetailsStorage.getCurrentUser());
            promptService.getPrompt(promptEntity);

            return response.response();

        } else {
            promptEntity.setPrompt(prompt);
            promptEntity.setResponse("no response given");
            promptEntity.setCreatedAt(LocalDateTime.now());
            return null;
        }
    }
}