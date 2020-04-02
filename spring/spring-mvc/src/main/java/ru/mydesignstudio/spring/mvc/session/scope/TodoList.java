package ru.mydesignstudio.spring.mvc.session.scope;

import java.util.ArrayList;
import java.util.List;

public class TodoList {
  private List<Todo> todos = new ArrayList<>();

  public List<Todo> findAll() {
    return todos;
  }

  public void add(Todo newTodo) {
    todos.add(newTodo);
  }
}
