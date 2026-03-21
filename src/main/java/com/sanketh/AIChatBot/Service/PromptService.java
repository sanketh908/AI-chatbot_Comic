package com.sanketh.AIChatBot.Service;

import com.sanketh.AIChatBot.Entity.Prompt;
import com.sanketh.AIChatBot.Repository.PromptRepository;
import org.springframework.stereotype.Service;

@Service
public class PromptService {
    private final PromptRepository promptRepository;

    public PromptService(PromptRepository promptRepository) {
        this.promptRepository = promptRepository;
    }
    public Prompt getPrompt(Prompt prompt) {
        return promptRepository.save(prompt);
    }
}
