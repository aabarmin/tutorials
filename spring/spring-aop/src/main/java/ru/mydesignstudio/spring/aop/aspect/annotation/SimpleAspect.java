package ru.mydesignstudio.spring.aop.aspect.annotation;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class SimpleAspect {
  @Before("execution(* ru.mydesignstudio.spring.aop.service.SomeService*.*(..))")
  public void advice_before() {
    System.out.println("Before method call");
  }

  @After("execution(* ru.mydesignstudio.spring.aop.service.SomeService*.*(..))")
  public void advice_after() {
    System.out.println("After method call");
  }

  @AfterReturning("execution(* ru.mydesignstudio.spring.aop.service.SomeService*.*(..))")
  public void advice_afterReturn() {
    System.out.println("After return");
  }

  @AfterThrowing("execution(* ru.mydesignstudio.spring.aop.service.SomeService*.*(..))")
  public void advice_afterException() {
    System.out.println("Exception was thrown");
  }

  @Around("execution(* ru.mydesignstudio.spring.aop.service.SomeService*.*(..))")
  public Object advice_around(ProceedingJoinPoint joinPoint) throws Throwable {
    try {
      System.out.println("Around before method");
      return joinPoint.proceed();
    } catch (Throwable e) {
      System.out.println("Around exception was caught");
      throw e;
    } finally {
      System.out.println("Around finally");
    }
  }

  @Before("execution(* ru.mydesignstudio.spring.aop.service.SomeService*.methodWithParameter(..)) && args(parameter)")
  public void advice_withArguments(String parameter) {
    System.out.println("Parameter passed to the aspect is " + parameter);
  }
}
