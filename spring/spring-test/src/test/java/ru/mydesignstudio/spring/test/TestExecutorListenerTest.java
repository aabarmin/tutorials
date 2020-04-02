package ru.mydesignstudio.spring.test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListener;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration
@TestExecutionListeners(
        value = {
                TestExecutorListenerTest.MyTestExecutorListener.class,
                TestExecutorListenerTest.MyAnotherTestExecutorListener.class
        },
        mergeMode = TestExecutionListeners.MergeMode.MERGE_WITH_DEFAULTS
)
public class TestExecutorListenerTest {
  @Autowired
  private SampleBean sampleBean;

  @Test
  public void checkContext() {
    assertNotNull(sampleBean);
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

  static class MyTestExecutorListener extends DependencyInjectionTestExecutionListener {

  }

  static class MyAnotherTestExecutorListener implements TestExecutionListener {

  }
}
