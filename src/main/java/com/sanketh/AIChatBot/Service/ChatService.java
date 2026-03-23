package com.sanketh.AIChatBot.Service;
import com.sanketh.AIChatBot.DTO.Request;
import com.sanketh.AIChatBot.DTO.Response;
import com.sanketh.AIChatBot.Entity.Prompt;
import com.sanketh.AIChatBot.Entity.User;
import com.sanketh.AIChatBot.Security.UserPrinciple;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;


import java.time.LocalDateTime;
import java.util.List;

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
    @Transactional
    public String getResponse(String prompt)
    {  Authentication authentication = SecurityContextHolder
            .getContext()
            .getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new IllegalStateException("No authenticated user");
        }

        UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
        User currentUser = userPrinciple.getUser();

        Request request= new Request(model, prompt, false);
        Response response=restClient.post().uri("/api/generate")
                .body(request)
                .retrieve()
                .body(Response.class);
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new IllegalStateException("No authenticated user");
        }

        Prompt promptEntity = new Prompt();
        if (response != null) {

            promptEntity.setPrompt(prompt);
            promptEntity.setResponse(response.response());
            promptEntity.setCreatedAt(LocalDateTime.now());
            promptService.getPrompt(promptEntity);
            promptEntity.setUser(currentUser);
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