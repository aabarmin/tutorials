package ru.mydesignstudio.spring.core;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.mydesignstudio.spring.core.annotation.scoped.proxy.ScopedProxyConfiguration;
import ru.mydesignstudio.spring.core.annotation.scoped.proxy.SingletonBean;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = ScopedProxyConfiguration.class)
public class AnnotationContextScopedProxyTest {
  @Autowired
  private SingletonBean singletonBean;

  @Test
  void check_contextStarts() {
    assertNotNull(singletonBean);
  }

  @Test
  void check_differentInstances() {
    assertNotEquals(singletonBean.doSomething(), singletonBean.doSomething());
  }
}
