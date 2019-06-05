package ru.mydesignstudio.spring.aop;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.mydesignstudio.spring.aop.aspect.with.annotation.WithAnnotationAspectConfiguration;
import ru.mydesignstudio.spring.aop.service.SomeService;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = WithAnnotationAspectConfiguration.class)
public class AspectJWithAnnotationsTest {
  @Autowired
  private SomeService someService;

  @Test
  void check_auditableWorks() {
    someService.methodWithParameter("param");
    someService.getResult();
  }
}
