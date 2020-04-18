package ru.mydesignstudio.microprofile.jpa.service.book;

import ru.mydesignstudio.microprofile.jpa.entity.BookEntity;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.Collection;

@RequestScoped
public class BookService {
  @Inject
  BookRepository bookRepository;

  @Transactional
  public Collection<BookEntity> findAll() {
    return bookRepository.listAll();
  }

  @Transactional
  public BookEntity save(BookEntity bookEntity) {
    bookRepository.persist(bookEntity);
    return bookEntity;
  }
}
