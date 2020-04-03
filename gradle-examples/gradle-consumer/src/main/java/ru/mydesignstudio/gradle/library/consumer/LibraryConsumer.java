package ru.mydesignstudio.gradle.library.consumer;

import ru.mydesignstudio.gradle.library.StringTrimmer;

import javax.inject.Inject;

public class LibraryConsumer {
  private final StringTrimmer trimmer;

  @Inject
  public LibraryConsumer(StringTrimmer trimmer) {
    this.trimmer = trimmer;
  }

  public String delegate(String sourceString) {
    return trimmer.trim(sourceString);
  }
}
