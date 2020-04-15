package ru.mydesignstudio.microprofile.jpa.service.category;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import ru.mydesignstudio.microprofile.jpa.entity.CategoryEntity;

import javax.inject.Inject;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class CategoryServiceTest {
  @Inject
  CategoryService categoryService;

  @Test
  void check_contextStarts() {
    assertNotNull(categoryService);
  }

  @Test
  void findAll_categoriesAreInPlace() {
    final Collection<CategoryEntity> categories = categoryService.findAll();

    assertAll(
            () -> assertNotNull(categories),
            () -> assertTrue(categories.size() > 0)
    );
  }
}