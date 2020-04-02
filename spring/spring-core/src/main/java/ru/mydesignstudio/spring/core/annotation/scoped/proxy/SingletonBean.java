package ru.mydesignstudio.spring.core.annotation.scoped.proxy;

public abstract class SingletonBean {
  public abstract PrototypeBean getPrototypeBean();

  public int doSomething() {
    return getPrototypeBean().hashCode();
  }
}
