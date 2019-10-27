package ru.mydesignstudio.spring.data.jpa.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@Import(DataSourceConfiguration.class)
@EnableJpaRepositories("ru.mydesignstudio.spring.data.jpa.repository")
public class CommonConfiguration {
}
