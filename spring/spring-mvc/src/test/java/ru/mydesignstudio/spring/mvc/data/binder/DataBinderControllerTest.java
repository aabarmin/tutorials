package ru.mydesignstudio.spring.mvc.data.binder;

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

@SpringJUnitWebConfig(DataBinderConfiguration.class)
class DataBinderControllerTest {
    private MockMvc mockMvc;

    @BeforeEach
    void setup(WebApplicationContext webApplicationContext) {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .alwaysDo(print())
                .build();
    }

    @Test
    void check_inputIsParsed() throws Exception {
        mockMvc.perform(get("/")
                .param("unparsedDate", "03-25-2019"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("month", 3))
                .andExpect(model().attribute("day", 25))
                .andExpect(model().attribute("year", 2019));
    }
}
