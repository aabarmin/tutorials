package ru.mydesignstudio.spring.core;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.mydesignstudio.spring.core.component.scan.filter.ShouldBeFound;
import ru.mydesignstudio.spring.core.component.scan.filter.ShouldNotBeFound;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {
        "classpath:custom-component-scan-context.xml"
})
public class ComponentScanFilterTest {
    @Autowired
    private ShouldBeFound shouldBeFound;
    @Autowired(required = false)
    private ShouldNotBeFound shouldNotBeFound;

    @Test
    public void check_contextStarts() {
        assertNotNull(shouldBeFound);
        assertNull(shouldNotBeFound);
    }
}
