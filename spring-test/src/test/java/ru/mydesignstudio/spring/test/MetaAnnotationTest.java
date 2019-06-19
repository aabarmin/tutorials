package ru.mydesignstudio.spring.test;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@MetaAnnotationTest.MyCustomTest
public class MetaAnnotationTest {
  @Autowired
  private SampleBean sampleBean;

  @Test
  public void contextStarts() {
    assertNotNull(sampleBean);
  }

  @Retention(RetentionPolicy.RUNTIME)
  @SpringJUnitConfig(ConfigurationForTest.class)
  @ActiveProfiles("dev")
  static @interface MyCustomTest {

  }

  @Configuration
  static class ConfigurationForTest {
    @Bean
    SampleBean sampleBean() {
      return new SampleBean();
    }
  }

  static class SampleBean {

  }
}
