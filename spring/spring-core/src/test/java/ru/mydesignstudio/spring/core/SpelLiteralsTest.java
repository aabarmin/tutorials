package ru.mydesignstudio.spring.core;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.SimpleEvaluationContext;

public class SpelLiteralsTest {
  private SpelExpressionParser parser;

  @BeforeEach
  void setUp() {
    parser = new SpelExpressionParser();
  }

  @Test
  void create_list() {
    final List<Integer> values = parser.parseExpression("{1, 2, 3, 4, 5}").getValue(List.class);

    assertAll(
        () -> assertNotNull(values),
        () -> assertEquals(3, (int) values.get(2))
    );
  }

  @Test
  void create_map() {
    final Map<String, String> map = parser.parseExpression("{firstName: 'test', lastName: 'another test'}").getValue(Map.class);

    assertAll(
        () -> assertNotNull(map),
        () -> assertEquals("test", map.get("firstName"))
    );
  }

  @Test
  void class_method() {
    final Integer value = parser.parseExpression("'123'.length()").getValue(Integer.class);

    assertAll(
        () -> assertNotNull(value),
        () -> assertEquals(3, (int) value)
    );
  }

  @Test
  void set_variable() {
    final EvaluationContext context = SimpleEvaluationContext.forReadWriteDataBinding().build();
    context.setVariable("test", "value");
    final String value = parser.parseExpression("#test").getValue(context, String.class);

    assertAll(
        () -> assertNotNull(value),
        () -> assertEquals("value", value)
    );
  }

  @Test
  void collections_filter() {
    final List<String> values = Arrays.asList(
        "first",
        "second",
        "third"
    );
    final List filtered = parser.parseExpression("#root.?[#this == 'first']").getValue(values, List.class);

    assertAll(
        () -> assertNotNull(filtered),
        () -> assertEquals(1, filtered.size())
    );
  }
}
