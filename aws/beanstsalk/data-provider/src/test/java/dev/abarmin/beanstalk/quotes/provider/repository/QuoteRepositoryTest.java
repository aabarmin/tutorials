package dev.abarmin.beanstalk.quotes.provider.repository;

import dev.abarmin.beanstalk.quotes.provider.domain.Quote;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
class QuoteRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private QuoteRepository uut;

    @Test
    void check_contextStarts() {
        assertThat(uut).isNotNull();
    }

    @Test
    void addRecordsAndGetRandom() {
        IntStream.range(0, 100)
                .mapToObj(index -> {
                    final Quote quote = new Quote();
                    quote.setText("Quote " + index);
                    return quote;
                })
                .forEach(entityManager::persist);

        IntStream.range(0, 100)
                .mapToObj(index -> uut.getRandomQuotes().get(0))
                .forEach(value -> assertThat(value).isNotNull());
    }
}