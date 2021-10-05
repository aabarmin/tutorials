package dev.abarmin.pact.provider;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.InputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ProductMockRepository implements ProductRepository {
    private final Map<Long, Product> products = new HashMap<>();

    @Value("classpath:MOCK_DATA.json")
    private Resource mockResource;

    @Autowired
    private ObjectMapper objectMapper;

    @PostConstruct
    public void init() throws Exception {
        try (final InputStream stream = mockResource.getInputStream()) {
            objectMapper.readerFor(Product.class)
                    .<Product>readValues(stream)
                    .readAll()
                    .forEach(p -> products.put(p.getId(), p));
        }
    }

    @Override
    public Collection<Product> findAll() {
        return products.values();
    }

    @Override
    public Product findOne(Long id) {
        return products.get(id);
    }
}
