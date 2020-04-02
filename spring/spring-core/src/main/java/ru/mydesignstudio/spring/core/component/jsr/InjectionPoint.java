package ru.mydesignstudio.spring.core.component.jsr;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class InjectionPoint {
    private final NamedComponent namedComponent;

    @Inject
    public InjectionPoint(NamedComponent namedComponent) {
        this.namedComponent = namedComponent;
    }

    public void doSomething() {
        System.out.println(namedComponent.hashCode());
    }
}
