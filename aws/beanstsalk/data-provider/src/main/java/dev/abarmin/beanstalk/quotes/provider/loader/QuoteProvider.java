package dev.abarmin.beanstalk.quotes.provider.loader;

import dev.abarmin.beanstalk.quotes.provider.domain.Quote;
import dev.abarmin.beanstalk.quotes.provider.repository.QuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class QuoteProvider {
    @Autowired
    private QuoteRepository repository;

    public Quote provide() {
        final long count = repository.count();
        final int randomRecord = new Random().nextInt((int) count);
        return repository.findAll(PageRequest.of(randomRecord, 1))
                .stream()
                .findFirst()
                .orElseThrow();
    }
}
