package ru.mydesignstudio.spring.core.lookup.method;

public abstract class CommandManager {
  public Command getCommand() {
    return createCommand();
  }

  public abstract Command createCommand();
}
