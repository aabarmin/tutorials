package ru.mydesignstudio.microprofile.jpa.service;

import ru.mydesignstudio.microprofile.jpa.entity.ItemEntity;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.Collection;

@ApplicationScoped
public class ItemDao {
  @Inject
  EntityManager entityManager;

  public ItemEntity save(ItemEntity item) {
    entityManager.persist(item);
    return item;
  }

  public Collection<ItemEntity> findAll() {
    final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
    final CriteriaQuery<ItemEntity> query = builder.createQuery(ItemEntity.class);
    query.select(query.from(ItemEntity.class));

    final TypedQuery<ItemEntity> typedQuery = entityManager.createQuery(query);

    return typedQuery.getResultList();
  }
}
