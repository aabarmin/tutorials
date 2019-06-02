package ru.mydesignstudio.spring.core.autowiring;

public class NeedsDependency {
  private Dependency dependency;

  public Dependency getDependency() {
    return dependency;
  }

  public void setDependency(Dependency dependency) {
    this.dependency = dependency;
  }
}
