package ru.mydesignstudio.microprofile.jpa.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "CATEGORIES")
public class CategoryEntity extends PanacheEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "CATEGORY_ID")
  public Long id;

  @NotNull
  @Size(min = 3, max = 1024)
  @Column(name = "CATEGORY_TITLE")
  public String title;
}
