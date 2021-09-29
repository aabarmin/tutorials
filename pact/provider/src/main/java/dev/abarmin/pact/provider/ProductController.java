package dev.abarmin.pact.provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/products")
    public Collection<Product> findAll() {
        return productRepository.findAll();
    }

    @GetMapping("/products/{id}")
    public Product findOne(@PathVariable("id") Long id) {
        return productRepository.findOne(id);
    }
}
