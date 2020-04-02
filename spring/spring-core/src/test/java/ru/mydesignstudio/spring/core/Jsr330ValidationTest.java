package ru.mydesignstudio.spring.core;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.mydesignstudio.spring.core.validation.jsr.JsrValidationConfiguration;
import ru.mydesignstudio.spring.core.validation.jsr.Person;
import ru.mydesignstudio.spring.core.validation.jsr.PersonValidationService;

import javax.validation.ConstraintViolation;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = JsrValidationConfiguration.class)
public class Jsr330ValidationTest {
    @Autowired
    private PersonValidationService personValidationService;

    @Test
    public void check_personIsValid() {
        final Set<ConstraintViolation<Person>> violations = personValidationService.validate(new Person());

        assertNotNull(violations);
        assertEquals(2, violations.size());
    }
}
