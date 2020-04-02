package ru.mydesignstudio.spring.core;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.SimpleEvaluationContext;

public class SpelContextTest {
  @Test
  void check_valueSets() {
    final Simple simple = new Simple();
    simple.getValues().add(false);

    final SimpleEvaluationContext context = SimpleEvaluationContext.forReadOnlyDataBinding().build();
    final SpelExpressionParser parser = new SpelExpressionParser();

    parser.parseExpression("values[0]").setValue(context, simple, true);

    assertTrue(simple.getValues().get(0));
  }

  public static class Simple {
    private List<Boolean> values = new ArrayList<>();

    public List<Boolean> getValues() {
      return values;
    }

    public void setValues(List<Boolean> values) {
      this.values = values;
    }
  }
}
