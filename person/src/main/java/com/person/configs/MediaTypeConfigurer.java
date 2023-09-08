package com.person.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MediaTypeConfigurer implements WebMvcConfigurer {

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
//        configureContentNegotiationViaParam(configurer); // para usar via param
        configureContentNegotiationViaHeader(configurer); // para usar via header
    }


    private void configureContentNegotiationViaParam(ContentNegotiationConfigurer configurer) {
        // Via Query Param. Example: http://localhost:8080/api/person/v1?mediaType=xml
        configurer.favorParameter(true) // accept param
                .parameterName("mediaType") // name of the param
                .ignoreAcceptHeader(true) // ignore param on the header
                .useRegisteredExtensionsOnly(false)
                .defaultContentType(org.springframework.http.MediaType.APPLICATION_JSON) // uses as default
                .mediaType("json", MediaType.APPLICATION_JSON) // accept json
                .mediaType("xml", MediaType.APPLICATION_XML); // accept xml
    }

    private void configureContentNegotiationViaHeader(ContentNegotiationConfigurer configurer) {
        // Via Query Param. Example: http://localhost:8080/api/person/v1?mediaType=xml
        configurer.favorParameter(false) // accept param
                .ignoreAcceptHeader(false) // ignore param on the header
                .useRegisteredExtensionsOnly(false)
                .defaultContentType(org.springframework.http.MediaType.APPLICATION_JSON) // uses as default
                .mediaType("json", MediaType.APPLICATION_JSON) // accept json
                .mediaType("xml", MediaType.APPLICATION_XML); // accept xml
    }
}
