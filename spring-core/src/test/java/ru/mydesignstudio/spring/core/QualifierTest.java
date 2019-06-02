package ru.mydesignstudio.spring.core;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.mydesignstudio.spring.core.qualifier.ToInject;

import static org.junit.Assert.assertNotNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {
        "classpath:qualifier-context.xml"
})
public class QualifierTest {
    @Autowired
    private ToInject unitUnderTest;

    @Test
    public void check_contextStarts() {
        assertNotNull(unitUnderTest);
    }
}
