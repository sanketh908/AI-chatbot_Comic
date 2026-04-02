package com.sanketh.AIChatBot.Config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI(){
        return new OpenAPI().info(new Info().title("AI ChatBot API MODLE Comic").version("1.0").description("API documentation for AI ChatBot application by Sanketh.")).
                servers(List.of(new Server().url("http://localhost:8080").description("Local server"),new Server().url("http://localhost:8081").description("cloud server")));
    }
}
