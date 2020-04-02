package ru.mydesignstudio.spring.mvc.controller.advice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.on;

@SpringJUnitWebConfig(MyCustomConfiguration.class)
class MyCustomControllerTest {
    private MockMvc mockMvc;

    @BeforeEach
    public void setup(WebApplicationContext context) {
        mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .alwaysDo(print())
                .build();
    }

    @Test
    void check_indexViewIsCorrect() throws Exception {
        mockMvc.perform(get(""))
                .andExpect(status().isOk())
                .andExpect(view().name("indexView"));
    }

    @Test
    void check_modelHasFieldFromAdvice() throws Exception {
        mockMvc.perform(get(""))
                .andExpect(status().isOk())
                .andExpect(model().attribute("currentUser", "User Name"));
    }

    @Test
    void check_exceptionHasNormalResponse() throws Exception {
        mockMvc.perform(get("/exception"))
                .andExpect(status().isOk())
                .andExpect(view().name("errorView"))
                .andExpect(model().attribute("errorMessage", "Something has happened"));
    }

    @Test
    void generateUriToControllerWithParameter() throws Exception {
        final String requestUri = MvcUriComponentsBuilder
                .fromMethodCall(on(MyCustomController.class).withParameter("someValue"))
                .buildAndExpand()
                .encode()
                .toUriString();

        mockMvc.perform(get(requestUri))
                .andExpect(status().isOk())
                .andExpect(view().name("parameterizedView"))
                .andExpect(model().attribute("valueAttribute", "someValue"));
    }
}
