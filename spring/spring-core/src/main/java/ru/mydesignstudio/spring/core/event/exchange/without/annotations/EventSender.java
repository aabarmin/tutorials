package ru.mydesignstudio.spring.core.event.exchange.without.annotations;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

@Component
public class EventSender implements ApplicationEventPublisherAware {
    private ApplicationEventPublisher eventPublisher;

    public void sendEvent() {
        eventPublisher.publishEvent(new CustomEvent(this));
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.eventPublisher = applicationEventPublisher;
    }
}
