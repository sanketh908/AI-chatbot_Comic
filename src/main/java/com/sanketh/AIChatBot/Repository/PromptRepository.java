package com.sanketh.AIChatBot.Repository;

import com.sanketh.AIChatBot.Entity.Prompt;
import com.sanketh.AIChatBot.Entity.User;
import jakarta.annotation.Nonnull;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PromptRepository extends JpaRepository<Prompt,Integer> {
    @Modifying
    @Transactional
    @Query("DELETE FROM Prompt p WHERE p.user.id = :userId")
    int deleteByUserId(@Param("userId") Integer userId);
    long deleteByUser(User user);

    List<Prompt> findByUser(User currentUser);
}
