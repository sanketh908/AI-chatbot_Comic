package com.sanketh.chatbot_backend.service;

import com.sanketh.chatbot_backend.dto.Message;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class ChatbotService {

    private final ChatClient chatClient;
    private final List<Message> conversationHistory = new CopyOnWriteArrayList<>();

    public ChatbotService(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    public String chat(String userMessage) {
        List<org.springframework.ai.chat.messages.Message> messages = new ArrayList<>();

        for (Message historyMsg : conversationHistory) {
            if ("user".equals(historyMsg.getRole())) {
                messages.add(new UserMessage(historyMsg.getContent()));
            } else {
                messages.add(new AssistantMessage(historyMsg.getContent()));
            }
        }
        messages.add(new UserMessage(userMessage));

        String response = chatClient.prompt()
                .messages(messages)
                .call()
                .content();

        conversationHistory.add(new Message("user", userMessage));
        conversationHistory.add(new Message("assistant", response));

        return response;
    }

    public List<Message> getHistory() {
        return Collections.unmodifiableList(conversationHistory);
    }

    public void clearHistory() {
        conversationHistory.clear();
    }
}
