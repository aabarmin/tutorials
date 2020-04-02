package ru.mydesignstudio.spring.core.validation.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class PersonValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return PersonToValidate.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "firstName", "First Name is empty");
        ValidationUtils.rejectIfEmpty(errors, "lastName", "Last Name is empty");

        final PersonToValidate person = PersonToValidate.class.cast(target);
        if (person.getFirstName().isEmpty()) {
            errors.reject("firstName", "wtf?");
        }
    }
}
