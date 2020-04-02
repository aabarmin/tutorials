package ru.mydesignstudio.spring.aop;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.mydesignstudio.spring.aop.aspect.annotation.AnnotationAspectConfiguration;
import ru.mydesignstudio.spring.aop.service.SomeService;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AnnotationAspectConfiguration.class)
public class AspectJAnnotationsTest {
  @Autowired
  private SomeService someService;

  @Test
  void check_contextStarts() {
    assertNotNull(someService);
  }

  @Test
  void check_execution() {
    someService.getResult();
  }

  @Test
  void check_exceptionThrown() {
    assertThrows(RuntimeException.class, () -> {
      someService.willThrowException();
    });
  }

  @Test
  void check_argumentsHaveBeenPassedToTheAdvice() {
    someService.methodWithParameter("Super argument");
  }
}
