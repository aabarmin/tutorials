package ru.mydesignstudio.spring.core;

import org.junit.jupiter.api.Test;
import org.springframework.expression.spel.standard.SpelExpression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import ru.mydesignstudio.spring.core.validation.jsr.Person;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SpringExpressionLanguageTest {
    private SpelExpressionParser expressionParser = new SpelExpressionParser();

    @Test
    public void check_spelSimple() {
        final SpelExpression expression = expressionParser.parseRaw("'Hello, World!'");
        final Object value = expression.getValue();

        assertAll(
                () -> assertNotNull(value),
                () -> assertEquals("Hello, World!", value),
                () -> assertEquals("Hello, World!", expression.getValue(String.class))
        );
    }

    @Test
    public void check_spellWithObject() {
        final SpelExpression expression = expressionParser.parseRaw("firstName");
        final Person person = new Person();
        person.setFirstName("First Name");

        assertAll(
                () -> assertNotNull(expression.getValue(person)),
                () -> assertEquals("First Name", expression.getValue(person, String.class))
        );
    }
}
