package com.ucsal.pimbas.mssoftware.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("MS-Software API")
                        .version("1.0.0")
                        .description("Microserviço para gerenciamento de softwares do Sistema de Laboratório")
                        .contact(new Contact()
                                .name("Equipe UCSAL")
                                .email("contato@ucsal.br")
                        )
                );
    }
}