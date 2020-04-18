package ru.mydesignstudio.microprofile.jpa.service.book;

import io.quarkus.runtime.Startup;
import io.quarkus.runtime.StartupEvent;
import ru.mydesignstudio.microprofile.jpa.entity.BookEntity;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.util.stream.IntStream;

@Startup
@ApplicationScoped
public class BookInitializer {
  @Inject
  BookService bookService;

  public void onStartup(@Observes StartupEvent startupEvent) {
    IntStream.range(0, 10)
            .mapToObj(this::toBook)
            .forEach(bookService::save);
  }

  private BookEntity toBook(int value) {
    final BookEntity entity = new BookEntity();
    entity.setTitle("Title " + value);
    return entity;
  }
}
