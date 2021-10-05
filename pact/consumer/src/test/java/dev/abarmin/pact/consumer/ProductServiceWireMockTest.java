package dev.abarmin.pact.consumer;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.web.client.RestTemplate;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Collection;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.*;

import static org.assertj.core.api.Assertions.*;

@SpringJUnitConfig({
        ProductService.class,
        ProductServiceWireMockTest.TestConfig.class
})
public class ProductServiceWireMockTest {
    @Autowired
    private ProductService productService;

    @Autowired
    private WireMockServer mockServer;

    @Value("classpath:product/findAll.json")
    private Resource findAllResource;

    @Value("classpath:product/findOne.json")
    private Resource findOneResource;

    @Test
    void findAll_shouldReturnAllProducts() throws Exception {
        mockServer.stubFor(
                get(urlEqualTo("/products"))
                        .willReturn(
                                aResponse()
                                        .withStatus(200)
                                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                                        .withBody(readResourceAsString(findAllResource))
                        )
        );

        final Collection<Product> products = productService.findAll();

        assertThat(products).isNotNull()
                .isNotEmpty()
                .hasSize(2);
    }

    @Test
    void findOne_shouldReturnSingleProduct() throws Exception {
        mockServer.stubFor(
                get(urlMatching("/products/\\d+"))
                        .willReturn(
                                aResponse()
                                        .withStatus(200)
                                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                                        .withBody(readResourceAsString(findOneResource))
                        )
        );

        final Product product = productService.findOne(1L);

        assertThat(product).isNotNull()
                .extracting("id", "name", "description")
                .contains(1L, "Name 1", "Description 1");
    }

    private String readResourceAsString(Resource resource) throws Exception {
        try (final InputStream stream = resource.getInputStream()) {
            return IOUtils.toString(stream, StandardCharsets.UTF_8);
        }
    }

    @Configuration
    static class TestConfig {
        @Bean(initMethod = "start", destroyMethod = "stop")
        public WireMockServer mockServer() {
            return new WireMockServer(options().dynamicPort());
        }

        @Bean
        public RestTemplate restTemplate(WireMockServer mockServer) {
            return new RestTemplateBuilder()
                    .rootUri(mockServer.baseUrl())
                    .build();
        }
    }
}
