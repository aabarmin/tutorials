package ru.mydesignstudio.microprofile.jpa.service.category;

import ru.mydesignstudio.microprofile.jpa.entity.CategoryEntity;

import javax.enterprise.context.ApplicationScoped;
import java.util.Collection;

@ApplicationScoped
public class CategoryDao {
  public Collection<CategoryEntity> findAll() {
    return CategoryEntity.listAll();
  }

  public CategoryEntity save(CategoryEntity entity) {
    entity.persist();
    return entity;
  }
}
