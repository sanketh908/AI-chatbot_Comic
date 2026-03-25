package com.sanketh.AIChatBot.DTO;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class PromptResponse {
    private String prompt;
    private String response;
    private LocalDateTime createdAt;



}
