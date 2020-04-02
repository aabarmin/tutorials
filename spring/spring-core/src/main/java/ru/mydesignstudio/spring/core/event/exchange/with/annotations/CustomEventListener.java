package ru.mydesignstudio.spring.core.event.exchange.with.annotations;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomEventListener {
    private final List<CustomEvent> events = new ArrayList<>();

    @EventListener
    public void handleCustomEvent(CustomEvent customEvent) {
        events.add(customEvent);
    }

    public List<CustomEvent> getEvents() {
        return events;
    }
}
