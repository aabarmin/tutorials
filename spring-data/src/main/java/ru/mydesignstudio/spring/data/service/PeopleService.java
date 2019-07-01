package ru.mydesignstudio.spring.data.service;

import ru.mydesignstudio.spring.data.model.Person;

import java.util.Collection;

public interface PeopleService {
    Collection<Person> findAll();

    Person findOne(int id);

    Person save(Person person);

    void throwsException();
}
