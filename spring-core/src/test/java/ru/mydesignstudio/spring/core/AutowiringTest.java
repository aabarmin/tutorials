package ru.mydesignstudio.spring.core;

import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.mydesignstudio.spring.core.autowiring.NeedsDependency;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {
    "classpath:autowiring-context.xml"
})
public class AutowiringTest {
  @Autowired
  private NeedsDependency needsDependency;

  @Test
  void check_contextStarts() {
    assertNotNull(needsDependency);
    assertNotNull(needsDependency.getDependency());
  }
}
