package ru.mydesignstudio.microprofile.jpa.service.item;

import io.quarkus.runtime.Startup;
import io.quarkus.runtime.StartupEvent;
import ru.mydesignstudio.microprofile.jpa.entity.ItemEntity;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.util.stream.IntStream;

@Startup
@ApplicationScoped
public class ItemInitializer {
  @Inject
  ItemService itemService;

  void onStartup(@Observes StartupEvent startupEvent) {
    IntStream.range(0, 10)
            .mapToObj(this::toItem)
            .forEach(itemService::save);
  }

  private ItemEntity toItem(int i) {
    final ItemEntity entity = new ItemEntity();
    entity.setTitle("Item " + i);
    return entity;
  }
}
