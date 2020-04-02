package ru.mydesignstudio.spring.data;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.mydesignstudio.spring.data.model.Person;
import ru.mydesignstudio.spring.data.service.MyCustomException;
import ru.mydesignstudio.spring.data.service.PeopleFacade;
import ru.mydesignstudio.spring.data.service.PeopleService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "classpath:annotation-transaction-context.xml")
@Sql("classpath:create-tables.sql")
public class AnnotationTransactionsRollbackTest {
    @Autowired
    private PeopleService peopleService;
    @Autowired
    private PeopleFacade peopleFacade;

    @Test
    void check_contextStarts() {
        assertNotNull(peopleFacade);
        assertNotNull(peopleService);
    }

    @Test
    void save_createNewPersonAndCheckItExists() {
        final int before = peopleService.findAll().size();

        peopleService.save(new Person(
                "FIRST NAME",
                "LAST NAME"
        ));

        final int after = peopleService.findAll().size();

        assertEquals(before + 1, after);
    }

    @Test
    void save_createNewPersonAndThrowRuntimeException() {
        final int before = peopleService.findAll().size();

        assertThrows(UnsupportedOperationException.class, () -> {
            peopleFacade.saveAndThrowRuntimeException(new Person(
                    "First Name",
                    "Last Name"
            ));
        });

        final int after = peopleService.findAll().size();

        assertEquals(before, after);
    }

    @Test
    void save_createNewPersonAndThrowCheckedException() {
        final int before = peopleService.findAll().size();

        assertThrows(Exception.class, () -> {
            peopleFacade.saveAndThrowCheckedException(new Person(
                    "First Name",
                    "Last Name"
            ));
        });

        final int after = peopleService.findAll().size();

        assertEquals(before + 1, after);
    }

    @Test
    void save_createNewPersonAndThrowCustomRuntimeException() {
        final int before = peopleService.findAll().size();

        assertThrows(MyCustomException.class, () -> {
            peopleFacade.saveAndThrowCustomRuntimeException(new Person(
                    "First Name",
                    "Last Name"
            ));
        });

        final int after = peopleService.findAll().size();

        assertEquals(before + 1, after);
    }
}
