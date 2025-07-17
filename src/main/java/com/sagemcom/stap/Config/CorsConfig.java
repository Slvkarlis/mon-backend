package com.sagemcom.stap.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
            .allowedOrigins("http://localhost:8081") // spécifie explicitement ton frontend
            .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // ajouter OPTIONS
            .allowedHeaders("*")
            .allowCredentials(true)  // si tu utilises cookies ou auth avec credentials
            .maxAge(3600);  // cache la réponse pré-vol pendant 1h
    }
}
