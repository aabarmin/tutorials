package dev.abarmin.pact.provider;

import java.util.Collection;

public interface ProductRepository {
    Collection<Product> findAll();

    Product findOne(Long id);
}
