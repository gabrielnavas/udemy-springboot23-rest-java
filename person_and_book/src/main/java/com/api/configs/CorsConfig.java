package com.api.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Value("${cors.originPatters:default}")
    private String corsOriginPatters;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        String[] allowerdOrigins = corsOriginPatters.split(",");
        registry.addMapping("/**")
//                .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH")
                .allowedMethods("*")
                .allowedOrigins(allowerdOrigins)
                .allowCredentials(true);
    }
}
