package com.sanketh.AIChatBot.DTO;

import java.time.LocalDateTime;

public record PromptResponse(String prompt,
                             String response,
                             LocalDateTime createdAt){
}
