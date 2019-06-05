package ru.mydesignstudio.spring.aop.configurable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;

@Configurable(preConstruction = true)
@Component
public class Account {
  @Autowired
  private SomeService someService;
}
