package dev.abarmin.beanstalk.quotes.provider.rest;

import dev.abarmin.beanstalk.quotes.provider.domain.Quote;
import dev.abarmin.beanstalk.quotes.provider.loader.QuoteProvider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(QuoteResource.class)
class QuoteResourceTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private QuoteProvider provider;

    @Test
    void quote_shouldReturnString() throws Exception {
        final Quote quote = new Quote();
        quote.setText("test");

        when(provider.provide()).thenReturn(quote);

        mockMvc.perform(get("/quote"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_PLAIN))
                .andExpect(content().string(is("test")));

        verify(provider, times(1)).provide();
    }

    @Test
    void quote_shouldThrowExceptionIfNoQuotes() throws Exception {
        mockMvc.perform(get("/quote"))
                .andExpect(status().is4xxClientError());
    }
}