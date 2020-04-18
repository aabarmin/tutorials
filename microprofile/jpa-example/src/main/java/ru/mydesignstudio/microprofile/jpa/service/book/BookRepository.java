package ru.mydesignstudio.microprofile.jpa.service.book;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import ru.mydesignstudio.microprofile.jpa.entity.BookEntity;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class BookRepository implements PanacheRepository<BookEntity> {
}
