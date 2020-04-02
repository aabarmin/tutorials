package ru.mydesignstudio.spring.core.component.declaration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {
    @Bean(name = "fromConfiguration")
    public SuperComponent superComponent() {
        return new SuperComponent();
    }
}
