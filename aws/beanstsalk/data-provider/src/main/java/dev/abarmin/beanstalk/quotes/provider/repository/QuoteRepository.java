package dev.abarmin.beanstalk.quotes.provider.repository;

import dev.abarmin.beanstalk.quotes.provider.domain.Quote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuoteRepository extends JpaRepository<Quote, Long> {
    @Query("select q from Quote as q order by RANDOM()")
    List<Quote> getRandomQuotes();
}
