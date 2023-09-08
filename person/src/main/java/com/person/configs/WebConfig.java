package com.person.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        // Via Query Param. Example: http://localhost:8080/api/person/v1?mediaType=xml
        configurer.favorParameter(true) // accept param
                .parameterName("mediaType") // name of the param
                .ignoreAcceptHeader(true) // ignore param on the header
                .useRegisteredExtensionsOnly(false)
                .defaultContentType(MediaType.APPLICATION_JSON) // uses as default
                .mediaType("json", MediaType.APPLICATION_JSON) // accept json
                .mediaType("xml", MediaType.APPLICATION_XML); // accept xml
    }
}
