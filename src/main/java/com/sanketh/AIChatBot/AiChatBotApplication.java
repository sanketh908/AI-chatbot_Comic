package com.sanketh.AIChatBot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class  AiChatBotApplication {

	public static void main(String[] args) {
		SpringApplication.run(AiChatBotApplication.class, args);
	}
	@Bean
	public PlatformTransactionManager transactionManager( ) {

	}
}
