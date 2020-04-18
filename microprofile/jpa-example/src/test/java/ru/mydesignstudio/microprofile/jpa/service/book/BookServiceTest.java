package ru.mydesignstudio.microprofile.jpa.service.book;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import ru.mydesignstudio.microprofile.jpa.entity.BookEntity;

import javax.inject.Inject;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
class BookServiceTest {
  @Inject
  BookService bookService;

  @Test
  void check_contextStarts() {
    assertNotNull(bookService);
  }

  @Test
  void findAll_booksAreInPlace() {
    final Collection<BookEntity> books = bookService.findAll();

    assertAll(
            () -> assertNotNull(books),
            () -> assertNotNull(books.size() > 0)
    );
  }
}