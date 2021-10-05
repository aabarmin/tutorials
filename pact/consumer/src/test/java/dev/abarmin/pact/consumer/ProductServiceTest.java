package dev.abarmin.pact.consumer;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.client.MockRestServiceServer;

import java.util.Collection;

import static org.assertj.core.api.Assertions.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.*;
import static org.springframework.test.web.client.response.MockRestResponseCreators.*;

@RestClientTest(ProductService.class)
@AutoConfigureWebClient(registerRestTemplate = true)
class ProductServiceTest {
    @Autowired
    private ProductService productService;

    @Autowired
    private MockRestServiceServer mockServer;

    @Value("classpath:product/findAll.json")
    private Resource findAllResource;

    @Value("classpath:product/findOne.json")
    private Resource findOneResource;

    @Test
    void check_contextStarts() {
        assertThat(productService).isNotNull();
    }

    @Test
    void findOne_responseShouldBeDeserialized() {
        mockServer.expect(method(HttpMethod.GET))
                .andExpect(requestTo("/products/1"))
                .andRespond(withSuccess(findOneResource, MediaType.APPLICATION_JSON));

        final Product found = productService.findOne(1L);

        assertThat(found).isNotNull()
                .extracting("id", "name", "description")
                .contains(1L, "Name 1", "Description 1");
    }

    @Test
    void findAll_responseShouldBeDeserialized() {
        mockServer.expect(method(HttpMethod.GET))
                .andExpect(requestTo("/products"))
                .andRespond(withSuccess(findAllResource, MediaType.APPLICATION_JSON));

        final Collection<Product> products = productService.findAll();

        assertThat(products).isNotNull()
                .isNotEmpty()
                .hasSize(2);
    }
}