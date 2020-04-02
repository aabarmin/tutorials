package ru.mydesignstudio.spring.aop.service;

import org.springframework.stereotype.Component;

@Component
public class SomeServiceImpl implements SomeService {
  @Override
  @Auditable
  public String getResult() {
    System.out.println("Method call");
    return "This is the result";
  }

  @Override
  public void willThrowException() {
    System.out.println("An exception will be thrown now");
    throw new RuntimeException();
  }

  @Override
  public String methodWithParameter(String argument) {
    System.out.println(String.format(
        "Argument is '%s'",
        argument
    ));
    return argument;
  }
}
