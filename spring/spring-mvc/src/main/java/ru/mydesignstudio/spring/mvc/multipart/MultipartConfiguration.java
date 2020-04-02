package ru.mydesignstudio.spring.mvc.multipart;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MultipartConfiguration {
    @Bean
    public MultipartController multipartController() {
        return new MultipartController();
    }
}
