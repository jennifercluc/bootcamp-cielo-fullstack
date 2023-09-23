package com.bootcamp.queuemanager.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenAPIConfig {

    @Value("${g6.openapi.dev-url}")
    private String devUrl;

    @Bean
    public OpenAPI myOpenAPI() {
        Server devServer = new Server();
        devServer.setUrl(devUrl);
        devServer.setDescription("URL do serviço em ambiente de desenvolvimento");

        Info info = new Info()
                .title("API do desafio em grup do Bootcamp Cielo Dev - AdaTech")
                .version("1.0")
                .description("Equipe G6: Jennifer Carolinne, Mateus Fellipe e Anselmo.");

        return new OpenAPI().info(info).servers(List.of(devServer));
    }
}
