package ru.mydesignstudio.spring.core;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.mydesignstudio.spring.core.event.exchange.with.annotations.CustomEventListener;
import ru.mydesignstudio.spring.core.event.exchange.with.annotations.CustomEventSender;
import ru.mydesignstudio.spring.core.event.exchange.with.annotations.EventsConfiguration;

import static org.junit.Assert.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = EventsConfiguration.class)
public class EventListenerWithAnnotationsTest {
    @Autowired
    private CustomEventListener eventListener;

    @Autowired
    private CustomEventSender eventSender;

    @Test
    public void check_eventSent() {
        eventSender.sendEvent();
        assertEquals(1, eventListener.getEvents().size());
    }
}
