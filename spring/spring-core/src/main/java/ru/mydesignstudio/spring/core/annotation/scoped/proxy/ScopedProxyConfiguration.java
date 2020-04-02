package ru.mydesignstudio.spring.core.annotation.scoped.proxy;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class ScopedProxyConfiguration {
  @Bean
  @Scope("prototype")
  public PrototypeBean prototypeBean() {
    return new PrototypeBean();
  }

  @Bean
  public SingletonBean singletonBean() {
    return new SingletonBean() {
      @Override
      public PrototypeBean getPrototypeBean() {
        return prototypeBean();
      }
    };
  }
}
