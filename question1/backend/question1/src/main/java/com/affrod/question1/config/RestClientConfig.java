package com.affrod.question1.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Configuration
public class RestClientConfig {

    @Value("${api.token}")
    private String apiToken;

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        ClientHttpRequestInterceptor authInterceptor = (request, body, execution) -> {
            request.getHeaders().set("Authorization", "Bearer " + apiToken);
            request.getHeaders().set("Content-Type", "application/json");
            return execution.execute(request, body);
        };

        restTemplate.setInterceptors(List.of(authInterceptor));
        return restTemplate;
    }
}
