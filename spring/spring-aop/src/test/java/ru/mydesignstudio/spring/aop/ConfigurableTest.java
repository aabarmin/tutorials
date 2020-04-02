package ru.mydesignstudio.spring.aop;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.mydesignstudio.spring.aop.configurable.Account;
import ru.mydesignstudio.spring.aop.configurable.ConfigurationForConfigurable;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = ConfigurationForConfigurable.class)
public class ConfigurableTest {
  @Test
  void check_something() {
    final Account account = new Account();

    assertNotNull(account);
  }
}
