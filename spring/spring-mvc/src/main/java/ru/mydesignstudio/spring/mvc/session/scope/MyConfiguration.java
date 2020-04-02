package ru.mydesignstudio.spring.mvc.session.scope;

import org.springframework.beans.factory.config.CustomScopeConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.context.support.SimpleThreadScope;
import org.springframework.web.context.WebApplicationContext;

@Configuration
public class MyConfiguration {
  @Bean
  @Profile("scoped")
  @Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
  public TodoList todoList() {
    return new TodoList();
  }

  @Bean
  public CustomScopeConfigurer scopeConfigurer() {
    final CustomScopeConfigurer configurer = new CustomScopeConfigurer();
    configurer.addScope(WebApplicationContext.SCOPE_SESSION, new SimpleThreadScope());
    return configurer;
  }

  @Bean
  @Profile("scoped")
  public TodoScopedController scopedBeanController() {
    return new TodoScopedController();
  }

  @Bean
  @Profile("session")
  public TodoSessionAttrController sessionAttrController() {
    return new TodoSessionAttrController();
  }
}
