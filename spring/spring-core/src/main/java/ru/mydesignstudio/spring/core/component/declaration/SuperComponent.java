package ru.mydesignstudio.spring.core.component.declaration;

import javax.annotation.PostConstruct;

public class SuperComponent {
    @PostConstruct
    public void init() {
        System.out.println("Post construct " + hashCode());
    }
}
