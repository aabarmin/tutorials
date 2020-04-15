package ru.mydesignstudio.microprofile.jpa.service.category;

import ru.mydesignstudio.microprofile.jpa.entity.CategoryEntity;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.Collection;

@RequestScoped
public class CategoryService {
  @Inject
  CategoryDao categoryDao;

  @Transactional
  public Collection<CategoryEntity> findAll() {
    return categoryDao.findAll();
  }

  @Transactional
  public CategoryEntity save(CategoryEntity entity) {
    return categoryDao.save(entity);
  }
}
