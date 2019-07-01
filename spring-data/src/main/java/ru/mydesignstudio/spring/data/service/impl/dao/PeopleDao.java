package ru.mydesignstudio.spring.data.service.impl.dao;

import ru.mydesignstudio.spring.data.model.Person;

import java.util.Collection;

public interface PeopleDao {
    Collection<Person> findAll();

    Person findOne(int id);

    Person save(Person person);
}
