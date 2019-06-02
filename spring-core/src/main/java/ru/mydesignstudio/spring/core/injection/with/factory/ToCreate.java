package ru.mydesignstudio.spring.core.injection.with.factory;

import org.springframework.beans.factory.ObjectFactory;

public class ToCreate {
    private ObjectFactory<ToInject> factory;

    public int doSomething() {
        return factory.getObject().doSomething();
    }

    public void setFactory(ObjectFactory<ToInject> factory) {
        this.factory = factory;
    }
}
