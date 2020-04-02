package ru.mydesignstudio.spring.core;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.mydesignstudio.spring.core.injection.with.factory.ToCreate;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {
        "classpath:injection-with-factory-context.xml"
})
public class InjectionWithFactoryTest {
    @Autowired
    private ToCreate toCreate;

    @Test
    public void check_contextStarts() {
        assertNotNull(toCreate);

        assertNotEquals(toCreate.doSomething(), toCreate.doSomething());
    }
}
