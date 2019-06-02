package ru.mydesignstudio.spring.core.qualifier;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class ToInject {
    private final ToBeQualified qualified;

    public ToInject(@Qualifier("main") ToBeQualified qualified) {
        this.qualified = qualified;
    }

    public ToBeQualified getQualified() {
        return qualified;
    }
}
