package ru.mydesignstudio.spring.core.component.declaration;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ComponentConfiguration {
    @Bean(name = "fromComponent")
    public SuperComponent superComponent() {
        return new SuperComponent();
    }
}
