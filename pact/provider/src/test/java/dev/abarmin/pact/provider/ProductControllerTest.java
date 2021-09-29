package dev.abarmin.pact.provider;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
class ProductControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductRepository productRepository;

    @Test
    void findAll_shouldReturnProducts() throws Exception {
        when(productRepository.findAll()).thenReturn(List.of(createProduct()));

        mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id").hasJsonPath())
                .andExpect(jsonPath("$[0].id").value(10));

        verify(productRepository, times(1)).findAll();
    }

    @Test
    void findOne_shouldReturnProduct() throws Exception {
        when(productRepository.findOne(anyLong())).thenReturn(createProduct());

        mockMvc.perform(get("/products/10"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").hasJsonPath())
                .andExpect(jsonPath("$.id").value("10"));

        verify(productRepository, times(1)).findOne(10L);
    }

    private Product createProduct() {
        final Product product = new Product();
        product.setId(10L);
        product.setName("Name");
        product.setDescription("Description");
        return product;
    }
}