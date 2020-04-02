package ru.mydesignstudio.spring.core.annotation.conditional;

import org.springframework.stereotype.Component;

@Component
public class SomeService {
    private final SomeDao someDao;

    public SomeService(SomeDao someDao) {
        this.someDao = someDao;
    }
}
