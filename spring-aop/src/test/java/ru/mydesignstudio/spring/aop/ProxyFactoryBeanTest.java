package ru.mydesignstudio.spring.aop;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.mydesignstudio.spring.aop.proxy.factory.Person;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {
        "classpath:proxy-factory-context.xml"
})
public class ProxyFactoryBeanTest {
    @Autowired
    private Person person;

    @Test
    void check_valueReceived() {
        assertNotNull(person.getFirstName());
    }
}
