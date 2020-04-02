package ru.mydesignstudio.spring.core.proxy.factory;

public class CustomServiceImpl implements CustomerService {
  @Override
  public void someMethod() {
    System.out.println("Hello, World!");
  }
}
