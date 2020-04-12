package ru.mydesignstudio.microprofile.service;

import io.quarkus.runtime.StartupEvent;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.util.stream.IntStream;

@ApplicationScoped
public class ItemInitializer {
  @Inject ItemService itemService;

  void onStart(@Observes StartupEvent startupEvent) {
    IntStream.range(1, 5)
            .mapToObj(value -> createItem(value))
            .forEach(itemService::save);
  }

  private Item createItem(int value) {
    final Item item = new Item();
    item.setTitle("Item " + value);
    return item;
  }
}
