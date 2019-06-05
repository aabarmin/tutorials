package ru.mydesignstudio.spring.aop.aspect.with.annotation;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import ru.mydesignstudio.spring.aop.service.Auditable;

@Component
@Aspect
public class AnnotatedAspect {
  @Before("execution(* ru.mydesignstudio.spring.aop.service.SomeService*.*(..)) && @annotation(auditable)")
  public void aspect_auditable(Auditable auditable) {
    System.out.println("Auditable method has been called");
  }
}
