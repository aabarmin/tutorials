package ru.mydesignstudio.spring.mvc.data.binder;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringJUnitWebConfig(DataBinderConfiguration.class)
class ExceptionHandlerControllerTest {
    private MockMvc mockMvc;

    @BeforeEach
    void setup(WebApplicationContext context) {
        mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .alwaysDo(print())
                .build();
    }

    @Test
    void check_exceptionWasHandled() throws Exception {
        mockMvc.perform(get("/exception"))
                .andExpect(view().name("exceptionView"));
    }
}
