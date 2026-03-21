package com.sanketh.AIChatBot.Repository;

import com.sanketh.AIChatBot.Entity.Prompt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository<Prompt,Integer> {
}
