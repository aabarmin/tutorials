package ru.mydesignstudio.spring.data.jpa.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import ru.mydesignstudio.spring.data.jpa.config.CommonConfiguration;
import ru.mydesignstudio.spring.data.jpa.model.Person;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@SpringJUnitConfig(CommonConfiguration.class)
class PeopleRepositoryTest {
    @Autowired
    private PeopleRepository peopleRepository;

    @Test
    void check_contextStarts() {
        assertNotNull(peopleRepository);
    }

    @Test
    void create_shouldCreateNewPerson() {
        final Person person = new Person();
        person.setFirstName("First Name");
        person.setLastName("Last Name");

        final Person savedPerson = peopleRepository.save(person);

        assertAll(
                () -> assertNotNull(savedPerson),
                () -> assertNotEquals(0, savedPerson.getId())
        );

        peopleRepository.findById(savedPerson.getId())
                .orElseThrow(() -> new RuntimeException("Person wasn't found"));
    }
}
