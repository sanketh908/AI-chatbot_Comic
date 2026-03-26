package com.sanketh.AIChatBot.Service;

import com.sanketh.AIChatBot.Entity.Prompt;
import com.sanketh.AIChatBot.Entity.User;
import com.sanketh.AIChatBot.Repository.PromptRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class PromptService {
    private final PromptRepository promptRepository;

   public void deleteAllPrompts(User currentUser) {
       promptRepository.deleteByUser(currentUser);

   }
    public PromptService(PromptRepository promptRepository) {
        this.promptRepository = promptRepository;
    }
    public Prompt getPrompt(Prompt prompt) {
        return promptRepository.save(prompt);
    }
   List<Prompt> findByUser(User currentUser) {
        return promptRepository.findByUser(currentUser);
    }
}
