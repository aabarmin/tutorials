package ru.mydesignstudio.spring.core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.PropertyValue;
import ru.mydesignstudio.spring.core.validation.validator.PersonToValidate;

import static org.junit.Assert.assertEquals;

public class BeanWrapperTest {
    private PersonToValidate unitToTest;

    @BeforeEach
    public void setup() {
        unitToTest = new PersonToValidate();
    }

    @Test
    public void check_propertyIsSet() {
        final BeanWrapper beanWrapper = new BeanWrapperImpl(unitToTest);
        beanWrapper.setPropertyValue("firstName", "value");

        assertEquals("value", unitToTest.getFirstName());
    }

    @Test
    public void check_propertyValueIsSet() {
        final BeanWrapper beanWrapper = new BeanWrapperImpl(unitToTest);
        beanWrapper.setPropertyValue(new PropertyValue("lastName", "value"));

        assertEquals("value", unitToTest.getLastName());
    }
}
