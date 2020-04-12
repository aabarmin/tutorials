package ru.mydesignstudio.microprofile.service;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.Collection;

@RequestScoped
public class ItemService {
  @Inject ItemDao itemDao;

  public Collection<Item> findAll() {
    return itemDao.findAll();
  }

  public Item save(Item item) {
    return itemDao.save(item);
  }
}
