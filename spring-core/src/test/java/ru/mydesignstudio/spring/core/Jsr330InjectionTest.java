package ru.mydesignstudio.spring.core;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.mydesignstudio.spring.core.component.jsr.InjectionPoint;

import javax.inject.Inject;

import static org.junit.Assert.assertNotNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {
        "classpath:jsr-330-context.xml"
})
public class Jsr330InjectionTest {
    @Inject
    private InjectionPoint injectionPoint;

    @Test
    public void check_contextStarts() {
        assertNotNull(injectionPoint);
    }
}
