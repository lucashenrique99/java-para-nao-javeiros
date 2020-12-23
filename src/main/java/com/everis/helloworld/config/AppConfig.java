package com.everis.helloworld.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

@Configuration
public class AppConfig {

    private static final String API_URL = "https://5fe1ea4f7a94870017681c00.mockapi.io/api";

    @Autowired
    private ObjectMapper mapper;

    @Bean
    public WebTarget webTarget() {
        return ClientBuilder.newClient()
                .target(API_URL);
    }

}
