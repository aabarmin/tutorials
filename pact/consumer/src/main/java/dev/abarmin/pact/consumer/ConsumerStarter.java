package dev.abarmin.pact.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class ConsumerStarter implements CommandLineRunner {
    @Autowired
    private ProductService productService;

    @Override
    public void run(String... args) throws Exception {
        productService.findAll().stream()
                .map(p -> String.format("%s - %s", p.getId(), p.getName()))
                .forEach(System.out::println);
    }
}
