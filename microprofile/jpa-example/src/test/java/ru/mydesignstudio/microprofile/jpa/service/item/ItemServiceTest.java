package ru.mydesignstudio.microprofile.jpa.service.item;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import ru.mydesignstudio.microprofile.jpa.entity.ItemEntity;

import javax.inject.Inject;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@QuarkusTest
class ItemServiceTest {
  @Inject
  ItemService itemService;

  @Test
  void check_contextStarts() {
    assertNotNull(itemService);
  }

  @Test
  void findAll_itemsExist() {
    final Collection<ItemEntity> items = itemService.findAll();

    assertNotNull(items);
    assertTrue(items.size() > 0);
  }
}