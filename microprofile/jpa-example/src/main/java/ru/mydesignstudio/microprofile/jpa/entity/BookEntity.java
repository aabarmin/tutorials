package ru.mydesignstudio.microprofile.jpa.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "BOOKS")
public class BookEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "BOOK_ID")
  private Long id;

  @Size(min = 3, max = 1024)
  @NotNull
  @Column(name = "BOOK_TITLE")
  private String title;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }
}
