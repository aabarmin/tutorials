package ru.mydesignstudio.microprofile.service;

import io.quarkus.qute.TemplateData;

@TemplateData
public class Item {
  private int id;
  private String title;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }
}
