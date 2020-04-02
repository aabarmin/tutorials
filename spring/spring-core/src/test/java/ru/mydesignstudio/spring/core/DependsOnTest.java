package ru.mydesignstudio.spring.core;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import org.junit.Rule;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.mydesignstudio.spring.core.depends.on.Manager;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {
    "classpath:depends-on-context.xml"
})
public class DependsOnTest {
  @Rule
  public SystemOutRule systemOutRule = new SystemOutRule().enableLog();

  @Autowired
  private Manager manager;

  @Test
  void check_contextStarts() {
//    assertThat(systemOutRule.getLog(), is("Manager\nClient"));
    assertThat(manager, is(notNullValue()));
  }
}
