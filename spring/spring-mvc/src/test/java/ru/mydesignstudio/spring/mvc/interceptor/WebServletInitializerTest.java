package ru.mydesignstudio.spring.mvc.interceptor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringJUnitWebConfig(InterceptorConfiguration.class)
class WebServletInitializerTest {
    private MockMvc mockMvc;

    @BeforeEach
    void setUp(WebApplicationContext webContext) {
        mockMvc = MockMvcBuilders.webAppContextSetup(webContext)
                .alwaysDo(print())
                .build();
    }

    @Test
    void check_contextStarts() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk());
    }
}
