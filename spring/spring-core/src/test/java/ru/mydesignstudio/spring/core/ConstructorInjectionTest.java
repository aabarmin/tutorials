package ru.mydesignstudio.spring.core;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.mydesignstudio.spring.core.constructor.CombinerObject;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {
    "classpath:constructor-injection.xml"
})
public class ConstructorInjectionTest {
  @Autowired
  private CombinerObject combinerObject;

  @Autowired
  @Qualifier("factoredCombinerObject")
  private CombinerObject factoredCombinerObject;

  @Test
  void check_contextStarts() {
    assertThat(combinerObject, is(notNullValue()));
    assertThat(factoredCombinerObject, is(notNullValue()));
    assertThat(combinerObject, is(not(factoredCombinerObject)));
  }
}
