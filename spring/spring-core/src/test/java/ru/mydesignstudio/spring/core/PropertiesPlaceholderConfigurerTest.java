package ru.mydesignstudio.spring.core;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.mydesignstudio.spring.core.placeholder.configurer.PlainBean;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {
        "classpath:placeholder-configurer.xml"
})
public class PropertiesPlaceholderConfigurerTest {
    @Autowired
    private PlainBean plainBean;

    @Test
    public void check_contextStarts() {
        System.out.println(plainBean.getValue());
    }
}
