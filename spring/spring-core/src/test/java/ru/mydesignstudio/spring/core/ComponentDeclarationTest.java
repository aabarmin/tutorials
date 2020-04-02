package ru.mydesignstudio.spring.core;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.mydesignstudio.spring.core.component.declaration.SuperComponent;

import javax.annotation.Resource;

import static org.junit.Assert.assertNotNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {
        "classpath:custom-component-declaration-context.xml"
})
public class ComponentDeclarationTest {
    @Resource(name = "fromConfiguration")
    private SuperComponent fromConfiguration;

    @Resource(name = "fromComponent")
    private SuperComponent fromComponent;

    @Test
    public void check_contextStarts() {
        assertNotNull(fromConfiguration);
        assertNotNull(fromComponent);
    }
}
