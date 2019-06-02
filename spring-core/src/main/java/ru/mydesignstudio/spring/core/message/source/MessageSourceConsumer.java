package ru.mydesignstudio.spring.core.message.source;

import org.springframework.context.MessageSource;

import java.util.Locale;

public class MessageSourceConsumer {
    private final MessageSource messageSource;

    public MessageSourceConsumer(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public String getMessage() {
        return messageSource.getMessage("my.message", null, Locale.getDefault());
    }
}
