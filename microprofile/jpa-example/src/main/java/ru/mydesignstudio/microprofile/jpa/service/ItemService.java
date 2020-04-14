package ru.mydesignstudio.microprofile.jpa.service;

import ru.mydesignstudio.microprofile.jpa.entity.ItemEntity;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.Collection;

@RequestScoped
public class ItemService {
  @Inject
  ItemDao itemDao;

  @Transactional
  public Collection<ItemEntity> findAll() {
    return itemDao.findAll();
  }

  @Transactional
  public ItemEntity save(ItemEntity item) {
    return itemDao.save(item);
  }
}
