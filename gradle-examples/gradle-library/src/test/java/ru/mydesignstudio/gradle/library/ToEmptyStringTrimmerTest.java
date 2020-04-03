package ru.mydesignstudio.gradle.library;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.mydesignstudio.gradle.library.config.LibraryModuleConfiguration;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class ToEmptyStringTrimmerTest {
  @Parameterized.Parameters
  public static Object[][] parameters() {
    return new Object[][]{
            { "" , "" },
            { " abc ", "abc" },
            { "ab c", "ab c" }
    };
  }

  private final String source;
  private final String target;

  public ToEmptyStringTrimmerTest(String source, String target) {
    this.source = source;
    this.target = target;
  }

  private StringTrimmer trimmer;

  @Before
  public void setup() {
    final Injector injector = Guice.createInjector(new LibraryModuleConfiguration());
    trimmer = injector.getInstance(StringTrimmer.class);
  }

  @Test
  public void testTrimmer() {
    assertEquals(target, trimmer.trim(source));
  }
}