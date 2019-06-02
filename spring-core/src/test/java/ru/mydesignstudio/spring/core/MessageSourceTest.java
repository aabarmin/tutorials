package ru.mydesignstudio.spring.core;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.mydesignstudio.spring.core.message.source.MessageSourceConsumer;

import java.util.Locale;

import static org.junit.Assert.assertNotNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {
        "classpath:message-source-context.xml"
})
public class MessageSourceTest {
    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private MessageSourceConsumer messageSourceConsumer;

    @Test
    public void check_contextStarts() {
        assertNotNull(applicationContext.getMessage("my.message", null, Locale.getDefault()));
    }

    @Test
    public void check_beanReceivedMessageSource() {
        assertNotNull(messageSourceConsumer.getMessage());
    }
}
