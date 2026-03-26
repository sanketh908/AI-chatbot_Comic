package com.sanketh.AIChatBot.Repository;

import com.sanketh.AIChatBot.Entity.Prompt;
import com.sanketh.AIChatBot.Entity.User;
import jakarta.annotation.Nonnull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PromptRepository extends JpaRepository<Prompt,Integer> {
    void deleteByUser(User user);
    void deleteById(@Nonnull Integer id);
    List<Prompt> findByUser(User currentUser);
}
