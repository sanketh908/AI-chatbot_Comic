package com.sanketh.AIChatBot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class  AiChatBotApplication   {

	public static void main(String[] args) {
		SpringApplication.run(AiChatBotApplication.class, args);
		System.out.println("AiChatBotApplication started successfully!");
	}
	@Bean
	public PlatformTransactionManager transactionManager(LocalContainerEntityManagerFactoryBean entityManagerFactory ) {
        assert entityManagerFactory.getObject() != null;
        return new JpaTransactionManager(entityManagerFactory.getObject());

	}
}
