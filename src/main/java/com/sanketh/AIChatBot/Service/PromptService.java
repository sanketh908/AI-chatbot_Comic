package com.sanketh.AIChatBot.Service;

import com.sanketh.AIChatBot.Entity.Prompt;
import com.sanketh.AIChatBot.Entity.User;
import com.sanketh.AIChatBot.Repository.PromptRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;


@Service
public class PromptService {
    private final PromptRepository promptRepository;

    @Transactional
   public boolean deleteAllPrompts(User currentUser) {
    long  deletecount=   promptRepository.deleteByUserId(currentUser.getId());
    return deletecount > 0;

   }
   @Transactional
   public void deleteById(Integer id,Integer currentUserId) {
        Optional<Prompt> prompt = promptRepository.findByIdAndUserId(id,currentUserId);
        if(prompt.isPresent()) {
            promptRepository.delete(prompt.get());
            promptRepository.flush();
        }
        else
        {
            throw new EntityNotFoundException("Prompt not found or not yours ");
        }
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
    public List<Prompt> findTop5ByUserIdOrderByCreatedAtDesc(Integer  userId) {
        return promptRepository.findTop5ByUserIdOrderByCreatedAtDesc( userId);
    }
}
