package dev.abarmin.pact.consumer;

import lombok.Data;

@Data
public class Product {
    private Long id;
    private String name;
    private String description;
}
