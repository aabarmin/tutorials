package ru.mydesignstudio.spring.test.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mydesignstudio.spring.test.web.model.Person;
import ru.mydesignstudio.spring.test.web.service.PersonService;

import java.util.Collection;

@Service
public class PersonServiceImpl implements PersonService {
    @Autowired
    private PersonDao personDao;

    @Override
    public Person findOne(int id) {
        return personDao.findOne(id);
    }

    @Override
    public Person save(Person person) {
        return personDao.save(person);
    }

    @Override
    public Collection<Person> findAll() {
        return personDao.findAll();
    }
}
