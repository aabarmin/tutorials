package dev.abarmin.aws.document.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DocumentDbTestApplication implements CommandLineRunner {
  @Autowired
  private BookRepository repository;

  public static void main(String[] args) {
    SpringApplication.run(DocumentDbTestApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    repository.deleteAll();

    repository.save(createBook("Clean Code", "Robert Martin"));
    repository.save(createBook("Software Craftsman", "Sandro Mancuso"));

    repository.findAll().stream()
        .map(Book::getTitle)
        .forEach(System.out::println);
  }

  private Book createBook(String title, String author) {
    final Book book = new Book();
    book.setTitle(title);
    book.setAuthor(author);
    return book;
  }
}
