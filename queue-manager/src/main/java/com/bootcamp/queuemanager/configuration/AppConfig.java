package com.bootcamp.queuemanager.configuration;

import com.bootcamp.queuemanager.dto.CustomerFeedbackDTO;
import com.bootcamp.queuemanager.util.Type;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.LinkedList;
import java.util.concurrent.ConcurrentHashMap;

@Configuration
public class AppConfig {

    @Bean
    public ConcurrentHashMap<Type, LinkedList<CustomerFeedbackDTO>> concurrentHashMap() {
        ConcurrentHashMap<Type, LinkedList<CustomerFeedbackDTO>> map = new ConcurrentHashMap<Type, LinkedList<CustomerFeedbackDTO>>();
        map.put(Type.ELOGIO, new LinkedList<>());
        map.put(Type.SUGESTAO, new LinkedList<>());
        map.put(Type.CRITICA, new LinkedList<>());

        return map;
    }

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("http://localhost:4200");
        corsConfiguration.addAllowedOrigin("http://localhost");
        corsConfiguration.addAllowedMethod("*");
        corsConfiguration.addAllowedHeader("*");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);

        return new CorsFilter(source);
    }
}
