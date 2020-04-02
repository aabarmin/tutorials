package ru.mydesignstudio.spring.test.web.service.impl;

import ru.mydesignstudio.spring.test.web.model.Person;

import java.util.Collection;

public interface PersonDao {
    Person findOne(int id);

    Person save(Person person);

    Collection<Person> findAll();
}
