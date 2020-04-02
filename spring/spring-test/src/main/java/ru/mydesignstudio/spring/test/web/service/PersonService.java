package ru.mydesignstudio.spring.test.web.service;

import ru.mydesignstudio.spring.test.web.model.Person;

import java.util.Collection;

public interface PersonService {
    Person findOne(int id);

    Person save(Person person);

    Collection<Person> findAll();
}
