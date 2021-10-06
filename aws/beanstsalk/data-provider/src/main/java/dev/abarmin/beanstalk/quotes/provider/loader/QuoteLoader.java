package dev.abarmin.beanstalk.quotes.provider.loader;

import dev.abarmin.beanstalk.quotes.provider.domain.Quote;
import dev.abarmin.beanstalk.quotes.provider.repository.QuoteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class QuoteLoader implements CommandLineRunner {
    @Autowired
    private QuoteRepository repository;

    @Autowired
    private QuoteTextProvider provider;

    @Override
    public void run(String... args) throws Exception {
        final long quotes = repository.count();
        log.info("Quotes count: {}", quotes);
        if (quotes == 0) {
            loadQuotes();
        }
    }

    private void loadQuotes() {
        provider.provide().stream()
                .map(q -> {
                    final Quote quote = new Quote();
                    quote.setText(q);
                    return quote;
                })
                .forEach(repository::save);
    }
}
