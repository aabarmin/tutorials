package ru.mydesignstudio.gradle.library;

import org.apache.commons.lang3.StringUtils;

public class ToEmptyStringTrimmer implements StringTrimmer {
  @Override
  public String trim(String sourceString) {
    return StringUtils.trimToEmpty(sourceString);
  }
}
