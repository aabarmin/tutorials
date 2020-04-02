package ru.mydesignstudio.spring.core;

import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.mydesignstudio.spring.core.annotation.scan.AnotherSimpleService;
import ru.mydesignstudio.spring.core.annotation.scan.ConfigurationWithScan;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {
    ConfigurationWithScan.class
})
public class AnnotationContextScanTest {
  @Autowired
  private AnotherSimpleService simpleService;

  @Test
  void check_contextStarts() {
    assertNotNull(simpleService);
  }
}
