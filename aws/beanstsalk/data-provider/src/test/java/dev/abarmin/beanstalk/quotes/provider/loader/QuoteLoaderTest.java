package dev.abarmin.beanstalk.quotes.provider.loader;

import dev.abarmin.beanstalk.quotes.provider.domain.Quote;
import dev.abarmin.beanstalk.quotes.provider.repository.QuoteRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringJUnitConfig(classes = {
        QuoteLoader.class
})
class QuoteLoaderTest {
    @Autowired
    private QuoteLoader uut;

    @MockBean
    private QuoteTextProvider provider;

    @MockBean
    private QuoteRepository repository;

    @Test
    void check_contextStarts() {
        assertThat(uut).isNotNull();
    }

    @Test
    void run_whenNoQuotesShouldLoadNew() throws Exception {
        when(repository.count()).thenReturn(0L);
        when(provider.provide()).thenReturn(List.of(
                "first",
                "second"
        ));

        uut.run();

        verify(provider, times(1)).provide();
        verify(repository, times(2)).save(any(Quote.class));
    }
}