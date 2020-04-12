package ru.mydesignstudio.microprofile.service;

import javax.enterprise.context.ApplicationScoped;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ApplicationScoped
public class ItemDao {
  private final Map<Integer, Item> items = new ConcurrentHashMap<>();

  public Collection<Item> findAll() {
    return items.values();
  }

  public Item save(Item item) {
    if (item.getId() == 0) {
      item.setId(items.size() + 1);
    }
    items.put(item.getId(), item);
    return item;
  }
}
