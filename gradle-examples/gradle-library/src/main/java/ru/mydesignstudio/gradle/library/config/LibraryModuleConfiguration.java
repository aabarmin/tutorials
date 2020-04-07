package ru.mydesignstudio.gradle.library.config;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import ru.mydesignstudio.gradle.library.StringTrimmer;
import ru.mydesignstudio.gradle.library.ToEmptyStringTrimmer;

public class LibraryModuleConfiguration extends AbstractModule {
  @Override
  protected void configure() {
    bind(StringTrimmer.class).to(ToEmptyStringTrimmer.class)
            .in(Scopes.SINGLETON);
  }
}
