package ru.mydesignstudio.spring.core;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.mydesignstudio.spring.core.lookup.method.Command;
import ru.mydesignstudio.spring.core.lookup.method.CommandManager;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {
    "classpath:lookup-method-context.xml"
})
public class FactoryMethodTest {
  @Autowired
  private CommandManager commandManager;

  @Test
  void check_contextStarts() {
    assertNotNull(commandManager);

    Set<Command> commands = new HashSet<>();
    commands.add(commandManager.getCommand());
    commands.add(commandManager.getCommand());
    commands.add(commandManager.getCommand());

    assertEquals(3, commands.size());
  }
}
