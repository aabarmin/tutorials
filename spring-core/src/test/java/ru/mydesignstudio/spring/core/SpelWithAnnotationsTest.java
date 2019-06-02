package ru.mydesignstudio.spring.core;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration
public class SpelWithAnnotationsTest {
  @Autowired
  private Simple simple;

  @Test
  void check_contextStarts() {
    assertNotNull(simple.value);
  }

  @Configuration
  static class ConfigurationForTest {
    @Bean
    public Simple simple(@Value("#{systemProperties['user.home']}") String value) {
      return new Simple(value);
    }
  }

  static class Simple {
    private final String value;

    public Simple(String value) {
      this.value = value;
    }
  }
}
