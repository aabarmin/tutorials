package ru.mydesignstudio.spring.data.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mydesignstudio.spring.data.annotation.ReadOnlyTx;
import ru.mydesignstudio.spring.data.model.Person;
import ru.mydesignstudio.spring.data.service.PeopleService;
import ru.mydesignstudio.spring.data.service.impl.dao.PeopleDao;

import java.util.Collection;

@Service
public class PeopleServiceImpl implements PeopleService {
    private final PeopleDao peopleDao;

    public PeopleServiceImpl(PeopleDao peopleDao) {
        this.peopleDao = peopleDao;
    }

    @Override
    @ReadOnlyTx
    public Collection<Person> findAll() {
        return peopleDao.findAll();
    }

    @Override
    @ReadOnlyTx
    public Person findOne(int id) {
        return peopleDao.findOne(id);
    }

    @Override
    @Transactional
    public Person save(Person person) {
        return peopleDao.save(person);
    }

    @Override
    public void throwsException() {
        throw new UnsupportedOperationException();
    }
}
