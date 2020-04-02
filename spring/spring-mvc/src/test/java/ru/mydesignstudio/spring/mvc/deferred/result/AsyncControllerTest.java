package ru.mydesignstudio.spring.mvc.deferred.result;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringJUnitWebConfig(AsyncControllerConfiguration.class)
class AsyncControllerTest {
    private MockMvc mockMvc;

    @BeforeEach
    void setup(WebApplicationContext context) {
        mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .alwaysDo(print())
                .build();
    }

    @Test
    void getDeferred_waitForResponse() throws Exception {
        final MvcResult mvcResult = mockMvc.perform(get("/deferred"))
                .andReturn();

        mockMvc.perform(asyncDispatch(mvcResult))
                .andExpect(content().string("Some result"))
                .andExpect(status().isOk());
    }

    @Test
    void getCallable_waitForResponse() throws Exception {
        final MvcResult mvcResult = mockMvc.perform(get("/callable"))
                .andReturn();

        mockMvc.perform(asyncDispatch(mvcResult))
                .andExpect(status().isOk())
                .andExpect(content().string("Another result"));
    }

    @Test
    void getStreamingResult_waitForResponse() throws Exception {
        final MvcResult mvcResult = mockMvc.perform(get("/streaming"))
                .andReturn();

        mockMvc.perform(asyncDispatch(mvcResult))
                .andExpect(status().isOk());
    }
}
