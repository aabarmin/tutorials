package ru.mydesignstudio.spring.mvc.resolver;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringJUnitWebConfig(MyConfiguration.class)
public class ControllerParamsTest {
    private MockMvc mockMvc;

    @BeforeEach
    void setup(WebApplicationContext context) {
        mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .alwaysDo(print())
                .build();
    }

    @Test
    void shouldGetFirstResponse() throws Exception {
        mockMvc.perform(get("/another"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("param", "value1"));
    }

    @Test
    void shouldGetSecondResponse() throws Exception {
        final MockHttpServletRequestBuilder requestBuilder = get("/another")
                .param("someParam", "someValue");

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(model().attribute("param", "value2"));
    }
}
