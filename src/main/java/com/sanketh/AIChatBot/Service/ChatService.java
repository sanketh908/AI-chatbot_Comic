package com.sanketh.AIChatBot.Service;

import com.sanketh.AIChatBot.Repository.ChatRepository;
import org.springframework.stereotype.Service;

@Service
public class ChatService {
 private final ChatRepository chatRepository;

    public ChatService(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }

}
