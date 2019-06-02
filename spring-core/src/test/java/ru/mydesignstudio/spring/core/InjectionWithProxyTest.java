package ru.mydesignstudio.spring.core;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.mydesignstudio.spring.core.injection.with.proxy.ParentBean;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {
        "classpath:injection-with-proxy-context.xml"
})
public class InjectionWithProxyTest {
    @Autowired
    private ParentBean parentBean;

    @Test
    void check_contextStarts() {
        assertNotNull(parentBean);
        assertNotNull(parentBean.getChildBean());

        assertNotEquals(parentBean.doSomething(), parentBean.doSomething());
    }
}
