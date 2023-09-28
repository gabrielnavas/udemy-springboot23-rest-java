package com.api.configs;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    /**
     * Links:
     * http://localhost:8080/v3/api-docs
     * http://localhost:8080/swagger-ui/index.html
     *
     * @return the OpenAPI with configurations
     */
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(
                        new Info()
                                .title("RESTful API with java 17 and Spring Boot 3")
                                .version("v1")
                                .description("This is a API for learning the framework spring boot ")
                                .termsOfService("Use and abuse ;)")
                                .license(
                                        new License()
                                                .name("Apache 2.0")
                                                .url("https://gabrielnavas.github.io")
                                )
                );
    }
}
