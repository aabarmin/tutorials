package dev.abarmin.pact.consumer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ConsumerConfiguration {
    @Bean
    public RestTemplate restTemplate(@Value("${consumer.root.url:http://localhost:8080}") String rootUrl) {
        return new RestTemplateBuilder()
                .rootUri(rootUrl)
                .build();
    }
}
