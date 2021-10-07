package dev.abarmin.beanstalk.quotes.provider.rest;

import dev.abarmin.beanstalk.quotes.provider.domain.Quote;
import dev.abarmin.beanstalk.quotes.provider.loader.QuoteProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class QuoteResource {
    @Autowired
    private QuoteProvider quoteProvider;

    @GetMapping("/quote")
    public String quote() {
        return Optional.ofNullable(quoteProvider.provide())
                .map(Quote::getText)
                .orElseThrow();
    }
}
