package com.sanketh.AIChatBot.Service;
import com.sanketh.AIChatBot.DTO.Request;
import com.sanketh.AIChatBot.DTO.Response;
import com.sanketh.AIChatBot.Entity.Prompt;
import com.sanketh.AIChatBot.Entity.User;
import com.sanketh.AIChatBot.Repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;


@Service
public class ChatService {

    private final UserDetailsService userDetailsService;
    private final UserRepository userRepository;
    private final PromptService promptService;
    private final RestClient restClient;
    private final String model;
    public ChatService(UserDetailsService userDetailsService, UserRepository userRepository, PromptService promptService, @Value("${ollama.base-url}") String baseUrl, @Value("${ollama.model}") String model) {
        this.userDetailsService = userDetailsService;
        this.userRepository = userRepository;
        this.promptService = promptService;
        this.restClient = RestClient.builder().baseUrl(baseUrl).build();
        this.model = model;
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
            promptService.getPrompt(promptEntity);
            promptEntity.setUser(userDetailsService.getCurrentUser());
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