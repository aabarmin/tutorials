package ru.mydesignstudio.spring.mvc.session.scope;

import java.util.Objects;

public class Todo {
  private int id;
  private String todo;
  private boolean done;

  public Todo() {
  }

  public Todo(String todo) {
    this.todo = todo;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getTodo() {
    return todo;
  }

  public void setTodo(String todo) {
    this.todo = todo;
  }

  public boolean isDone() {
    return done;
  }

  public void setDone(boolean done) {
    this.done = done;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Todo todo1 = (Todo) o;
    return getId() == todo1.getId() &&
        isDone() == todo1.isDone() &&
        Objects.equals(getTodo(), todo1.getTodo());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId(), getTodo(), isDone());
  }
}
