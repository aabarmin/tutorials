package ru.mydesignstudio.spring.core;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.mydesignstudio.spring.core.collection.merge.ComplexObject;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {
    "classpath:collection-merging-context.xml"
})
public class CollectionMergingTest {
  @Autowired
  private ComplexObject complexObject;

  @Test
  void check_contextStarts() {
    assertThat(complexObject, is(notNullValue()));
    assertThat(complexObject.getProps().size(), is(3));
  }
}
