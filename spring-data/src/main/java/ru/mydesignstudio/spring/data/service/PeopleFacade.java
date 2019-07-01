package ru.mydesignstudio.spring.data.service;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.mydesignstudio.spring.data.model.Person;

@Component
@Transactional
public class PeopleFacade {
    private final PeopleService peopleService;

    public PeopleFacade(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    public void saveAndThrowRuntimeException(Person person) {
        peopleService.save(person);
        throw new UnsupportedOperationException();
    }

    public void saveAndThrowCheckedException(Person person) throws Exception {
        peopleService.save(person);
        throw new Exception();
    }

    @Transactional(noRollbackFor = MyCustomException.class)
    public void saveAndThrowCustomRuntimeException(Person person) {
        peopleService.save(person);
        throw new MyCustomException();
    }
}
