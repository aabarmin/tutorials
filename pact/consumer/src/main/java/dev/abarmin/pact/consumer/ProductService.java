package dev.abarmin.pact.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    private RestTemplate restTemplate;

    public Collection<Product> findAll() {
        return restTemplate.exchange(
                "/products",
                HttpMethod.GET,
                requestEntity(),
                new ParameterizedTypeReference<List<Product>>() {}
        ).getBody();
    }

    private HttpEntity requestEntity() {
        final HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        return new HttpEntity<>(headers);
    }

    public Product findOne(Long id) {
        return restTemplate.exchange(
                "/products/{id}",
                HttpMethod.GET,
                requestEntity(),
                Product.class,
                id
        ).getBody();
    }
}
