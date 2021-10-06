package dev.abarmin.beanstalk.quotes.provider.loader;

import dev.abarmin.beanstalk.quotes.provider.domain.Quote;
import dev.abarmin.beanstalk.quotes.provider.repository.QuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class QuoteProvider {
    @Autowired
    private QuoteRepository repository;

    public Quote provide() {
        return repository.getRandomQuotes()
                .stream()
                .findFirst()
                .orElse(null);
    }
}
