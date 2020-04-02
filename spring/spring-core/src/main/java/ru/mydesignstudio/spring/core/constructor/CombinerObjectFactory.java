package ru.mydesignstudio.spring.core.constructor;

public class CombinerObjectFactory {
  public static CombinerObject newCombinerObject(DependencyOne one, DependencyTwo two) {
    return new CombinerObject(one, two);
  }
}
