package ru.mydesignstudio.spring.aop;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.mydesignstudio.spring.aop.pooling.PersonConnection;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {
        "classpath:pooling-factory-context.xml"
})
public class BeanPoolingTest {
    @Autowired
    private PersonConnection personConnection;

    @Test
    void check_contextStarts() {
        assertNotNull(personConnection);
    }

    @Test
    void check_newBeanEveryInvocation() {
        for (int i = 0; i < 1000; i++) {
            personConnection.getHashCode();
        }
    }
}
