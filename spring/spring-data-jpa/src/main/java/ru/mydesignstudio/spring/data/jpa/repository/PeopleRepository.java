package ru.mydesignstudio.spring.data.jpa.repository;

import org.springframework.data.repository.CrudRepository;
import ru.mydesignstudio.spring.data.jpa.model.Person;

public interface PeopleRepository extends CrudRepository<Person, Integer> {
}
