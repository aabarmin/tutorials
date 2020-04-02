package ru.mydesignstudio.spring.core.annotation.conditional;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.util.Arrays;

public class ConditionalOnProfile implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        if (context.getEnvironment() != null) {
            final String[] profiles = context.getEnvironment().getActiveProfiles();
            return Arrays.asList(profiles).contains(metadata.getAnnotationAttributes(SomeAmazingCondition.class.getName()).get("value"));
        }
        return false;
    }
}
