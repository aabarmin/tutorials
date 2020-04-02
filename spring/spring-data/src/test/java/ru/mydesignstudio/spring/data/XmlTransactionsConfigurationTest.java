package ru.mydesignstudio.spring.data;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.mydesignstudio.spring.data.service.PeopleService;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "classpath:declarative-transaction-context.xml")
public class XmlTransactionsConfigurationTest {
    @Autowired
    private PeopleService peopleService;

    @Test
    void check_contextStarts() {
        assertNotNull(peopleService);
    }

    @Test
    void check_createNewPerson() {
        assertThrows(UnsupportedOperationException.class, () -> {
            peopleService.throwsException();
        });
    }
}
