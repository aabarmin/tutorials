package ru.mydesignstudio.spring.core.validation.jsr;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.validation.Validator;

@Configuration
@ComponentScan("ru.mydesignstudio.spring.core.validation.jsr")
public class JsrValidationConfiguration {
    @Bean
    public Validator validator() {
        return new LocalValidatorFactoryBean();
    }
}
