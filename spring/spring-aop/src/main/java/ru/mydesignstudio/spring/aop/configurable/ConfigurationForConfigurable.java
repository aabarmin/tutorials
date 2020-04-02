package ru.mydesignstudio.spring.aop.configurable;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;

@Configuration
@EnableSpringConfigured
@ComponentScan("ru.mydesignstudio.spring.aop.configurable")
public class ConfigurationForConfigurable {
}
