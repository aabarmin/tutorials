package ru.mydesignstudio.spring.core.event.exchange.without.annotations;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EventListener implements ApplicationListener<CustomEvent> {
    private final List<CustomEvent> events = new ArrayList<>();

    @Override
    public void onApplicationEvent(CustomEvent event) {
        this.events.add(event);
    }

    public List<CustomEvent> getEvents() {
        return this.events;
    }
}
