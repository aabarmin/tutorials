package ru.mydesignstudio.gradle.library.consumer;

import nl.pvanassen.guicejunitrunner.GuiceJUnitRunner;
import nl.pvanassen.guicejunitrunner.GuiceModules;
import org.junit.Test;
import org.junit.runner.RunWith;
import ru.mydesignstudio.gradle.library.config.LibraryModuleConfiguration;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;

@RunWith(GuiceJUnitRunner.class)
@GuiceModules({
        LibraryConsumerConfiguration.class,
        LibraryModuleConfiguration.class
})
public class LibraryConsumerTest {
  @Inject
  private LibraryConsumer consumer;

  @Test
  public void test() {
    assertEquals("abc", consumer.delegate(" abc "));
  }
}