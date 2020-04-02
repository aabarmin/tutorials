package ru.mydesignstudio.spring.core;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.mydesignstudio.spring.core.resources.ResourcesConfiguration;

import static org.junit.Assert.assertNotNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = ResourcesConfiguration.class)
public class ResourcesTest {
    @Autowired
    private ApplicationContext applicationContext;

    @Test
    public void getResource_fromClassPath() {
        final Resource resource = applicationContext.getResource("classpath:autowiring-context.xml");

        assertNotNull(resource);
    }
}
