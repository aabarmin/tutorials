package ru.mydesignstudio.spring.core;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.mydesignstudio.spring.core.event.exchange.without.annotations.EventListener;
import ru.mydesignstudio.spring.core.event.exchange.without.annotations.EventSender;
import ru.mydesignstudio.spring.core.event.exchange.without.annotations.EventsConfiguration;

import static org.junit.Assert.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = EventsConfiguration.class)
public class EventListenerWithoutAnnotationsTest {
    @Autowired
    private EventListener eventListener;
    @Autowired
    private EventSender eventSender;

    @Test
    public void check_messageHasBeenSend() {
        eventSender.sendEvent();
        assertEquals(1, eventListener.getEvents().size());
    }
}
