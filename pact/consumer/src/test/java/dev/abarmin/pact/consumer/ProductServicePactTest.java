package dev.abarmin.pact.consumer;

import au.com.dius.pact.consumer.MockServer;
import au.com.dius.pact.consumer.dsl.*;
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.core.model.RequestResponsePact;
import au.com.dius.pact.core.model.annotations.Pact;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RootUriTemplateHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;

import static org.assertj.core.api.Assertions.*;

@ExtendWith({
        SpringExtension.class,
        PactConsumerTestExt.class
})
@ContextConfiguration(classes = {
        ProductService.class,
        ProductServicePactTest.TestConfig.class
})
@PactTestFor(providerName = "ProductService")
class ProductServicePactTest {
    @Autowired
    private ProductService productService;

    @Autowired
    private RestTemplate restTemplate;

    @Pact(consumer = "ProductCatalogue")
    public RequestResponsePact findAll(PactDslWithProvider builder) {
        return builder
                .given("products exist")
                    .uponReceiving("get all products")
                    .path("/products")
                    .method("GET")
                .willRespondWith()
                    .status(200)
                    .body(
                            LambdaDsl.newJsonArrayMinLike(10, array ->
                                            array.object(object -> {
                                                object.integerType("id");
                                                object.stringType("name");
                                                object.stringType("description");
                                            }))
                                    .build()
                    )
                .toPact();
    }

    @Pact(consumer = "ProductCatalogue")
    public RequestResponsePact findSingle(PactDslWithProvider builder) {
        return builder
                .given("products exist")
                    .uponReceiving("get single product")
                    .matchPath("/products/\\d+")
                    .method("GET")
                .willRespondWith()
                    .status(200)
                    .body(
                            LambdaDsl.newJsonBody(object -> {
                                object.integerType("id");
                                object.stringType("name");
                                object.stringType("description");
                            }).build()
                    )
                .toPact();
    }

    @Test
    @PactTestFor(pactMethod = "findAll")
    void findAll(MockServer mockServer) {
        RootUriTemplateHandler.addTo(restTemplate, mockServer.getUrl());

        final Collection<Product> products = productService.findAll();

        assertThat(products).isNotNull()
                .isNotEmpty();
    }

    @Test
    @PactTestFor(pactMethod = "findSingle")
    void findOne(MockServer mockServer) {
        RootUriTemplateHandler.addTo(restTemplate, mockServer.getUrl());

        final Product product = productService.findOne(10L);

        assertThat(product).isNotNull();
    }

    @Configuration
    static class TestConfig {
        @Bean
        public RestTemplate restTemplate() {
            return new RestTemplate();
        }
    }
}