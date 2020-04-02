package ru.mydesignstudio.spring.aop.service;

public interface SomeService {
  String getResult();

  void willThrowException();

  String methodWithParameter(String argument);
}
