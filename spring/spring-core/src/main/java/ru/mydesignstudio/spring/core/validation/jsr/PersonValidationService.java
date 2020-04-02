package ru.mydesignstudio.spring.core.validation.jsr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

@Service
public class PersonValidationService {
    private final Validator validator;

    @Autowired
    public PersonValidationService(Validator validator) {
        this.validator = validator;
    }

    public Set<ConstraintViolation<Person>> validate(Person person) {
        return validator.validate(person);
    }
}
