package com.example.conta_bancaria.infrastructure.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI oficinaOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API - Conta Bancária")
                        .description("Cadastro e gestão de serviços de uma Conta Bancária.")
                        .version("1.0")
                        .contact(new Contact()
                                .name("Equipe Bancária")
                                .email("suporte@banco.com"))
                );
    }
}
