package ru.mydesignstudio.spring.mvc.model.attribute;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringJUnitWebConfig(ModelAttributeConfiguration.class)
class ModelAttributeControllerTest {
    private MockMvc mockMvc;

    @BeforeEach
    void setup(WebApplicationContext context) {
        mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .alwaysDo(print())
                .build();
    }

    @Test
    void modelAttribute_addedToModel() throws Exception {
        mockMvc.perform(get("/model/attribute"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("common", "added"));
    }
}
