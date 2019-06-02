package ru.mydesignstudio.spring.core.constructor;

public class CombinerObject {
  private final DependencyOne dependencyOne;
  private final DependencyTwo dependencyTwo;

  public CombinerObject(DependencyOne dependencyOne, DependencyTwo dependencyTwo) {
    this.dependencyOne = dependencyOne;
    this.dependencyTwo = dependencyTwo;
  }
}
