package com.apirest.Api_Rest.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
public class CorsConfig {

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuratio = new CorsConfiguration();
        configuratio.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
        configuratio.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
        configuratio.setAllowedHeaders(Arrays.asList("*"));
        configuratio.setAllowCredentials(true);
        configuratio.setMaxAge(3600L);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuratio);
        return source;
    }
}
