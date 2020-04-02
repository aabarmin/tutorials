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
import ru.mydesignstudio.spring.core.factory.ClientService;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {
    "classpath:factory-methods.xml"
})
public class BeanFactoryTest {
  @Autowired
  @Qualifier("clientService")
  private ClientService clientService;

  @Autowired
  @Qualifier("anotherClientService")
  private ClientService anotherClientService;

  @Test
  void check_contextStarts() {
    assertThat(clientService, is(notNullValue()));
    assertThat(anotherClientService, is(notNullValue()));

    assertThat(clientService, is(not(anotherClientService)));
  }
}
