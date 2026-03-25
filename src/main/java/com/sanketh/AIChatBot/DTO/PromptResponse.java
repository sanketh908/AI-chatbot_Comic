package com.sanketh.AIChatBot.DTO;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class PromptResponse {
    private String prompt;
    private String response;
    private LocalDateTime createdAt;


    public PromptResponse(String prompt, String response, LocalDateTime createdAt) {
        this.prompt = prompt;
        this.response = response;
        this.createdAt = createdAt;
    }
}
