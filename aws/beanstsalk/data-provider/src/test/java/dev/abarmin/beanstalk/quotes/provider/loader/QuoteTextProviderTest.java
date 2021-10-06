package dev.abarmin.beanstalk.quotes.provider.loader;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.Collection;

import static org.assertj.core.api.Assertions.*;

@SpringJUnitConfig(QuoteTextProvider.class)
class QuoteTextProviderTest {
    @Autowired
    private QuoteTextProvider uut;

    @Test
    void check_contextStarts() {
        assertThat(uut).isNotNull();
    }

    @Test
    void provide_stringsAreReturned() {
        final Collection<String> provided = uut.provide();

        assertThat(provided)
                .isNotNull()
                .isNotEmpty();
    }
}