package ru.mydesignstudio.gradle.library.consumer;

import com.google.inject.AbstractModule;

public class LibraryConsumerConfiguration extends AbstractModule {
  @Override
  protected void configure() {
    bind(LibraryConsumer.class);
  }
}
