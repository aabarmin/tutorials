package ru.mydesignstudio.spring.core;

import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.mydesignstudio.spring.core.annotation.context.ConfigurationClass;
import ru.mydesignstudio.spring.core.annotation.context.SimpleService;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {
    ConfigurationClass.class
})
public class AnnotationContextTest {
  @Autowired
  private SimpleService simpleService;

  @Test
  void check_contextStarts() {
    assertNotNull(simpleService);
  }
}
