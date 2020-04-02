package ru.mydesignstudio.spring.core;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.mydesignstudio.spring.core.proxy.factory.CustomerService;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {
    "classpath:proxy-factory-context.xml"
})
public class ProxyFactoryTest {
  @Autowired
  private CustomerService customerService;
  @Autowired
  private CustomerService customerServiceProxy;

  @Test
  void check_contextStarts() {
    assertThat(customerService, is(notNullValue()));
    assertThat(customerServiceProxy, is(notNullValue()));
    assertThat(customerService, is(not(customerServiceProxy)));
  }
}
