package ru.mydesignstudio.spring.core.annotation.context;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigurationClass {
  @Bean
  public SimpleService simpleService() {
    return new SimpleService();
  }
}
