package ru.mydesignstudio.microprofile.jpa.service.category;

import io.quarkus.runtime.Startup;
import io.quarkus.runtime.StartupEvent;
import ru.mydesignstudio.microprofile.jpa.entity.CategoryEntity;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.util.stream.IntStream;

@Startup
@ApplicationScoped
public class CategoryInitializer {
  @Inject
  CategoryService categoryService;

  void onStartup(@Observes StartupEvent event) {
    IntStream.range(0, 10)
            .mapToObj(this::toCategory)
            .forEach(categoryService::save);
  }

  private CategoryEntity toCategory(int value) {
    final CategoryEntity entity = new CategoryEntity();
    entity.title = "Title " + value;
    return entity;
  }
}
