package ru.mydesignstudio.spring.core.event.exchange.without.annotations;

import org.springframework.context.ApplicationEvent;

public class CustomEvent extends ApplicationEvent {
    public CustomEvent(Object source) {
        super(source);
    }
}
