package ru.mydesignstudio.spring.test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit.jupiter.DisabledIf;
import org.springframework.test.context.junit.jupiter.EnabledIf;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig(SpringJUnitConfigTest.ConfigurationForTest.class)
public class SpringJUnitConfigTest {
  @Autowired
  private SampleBean sampleBean;

  @Test
  void check_contextStarts() {
    assertNotNull(sampleBean);
  }

  @Test
  @EnabledIf("#{systemProperties['os.name'].toLowerCase().contains('mac')}")
  void conditionalTest_enabledOnMac() {
    assertTrue(true);
  }

  @Test
  @DisabledIf("#{systemProperties['os.name'].toLowerCase().contains('mac')}")
  void conditionalTest_disabledOnMac() {
    assertTrue(true);
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
