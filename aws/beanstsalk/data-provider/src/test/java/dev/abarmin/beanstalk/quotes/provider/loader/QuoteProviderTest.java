package dev.abarmin.beanstalk.quotes.provider.loader;

import dev.abarmin.beanstalk.quotes.provider.domain.Quote;
import dev.abarmin.beanstalk.quotes.provider.repository.QuoteRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringJUnitConfig(QuoteProvider.class)
public class QuoteProviderTest {
    @MockBean
    private QuoteRepository repository;

    @Autowired
    private QuoteProvider uut;

    @Test
    void check_contextStarts() {
        assertThat(uut).isNotNull();
    }

    @Test
    void provide_shouldReturnNextQuote() {
        final Quote quote = new Quote();
        quote.setText("My quote");
        when(repository.count()).thenReturn(10L);
        when(repository.findAll(any(Pageable.class))).thenReturn(new PageImpl<Quote>(List.of(quote)));

        final Quote extracted = uut.provide();

        assertThat(extracted).isNotNull()
                .isEqualTo(quote);
    }
}
