package ru.mydesignstudio.spring.core;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.mydesignstudio.spring.core.annotation.conditional.ConditionalConfiguration;
import ru.mydesignstudio.spring.core.annotation.conditional.SomeService;

import static org.junit.Assert.assertNotNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {
        ConditionalConfiguration.class
})
@ActiveProfiles("first")
public class ConditionalConfigurationTest {
    @Autowired
    private SomeService someService;

    @Test
    public void check_contextStarts() {
        assertNotNull(someService);
    }
}
