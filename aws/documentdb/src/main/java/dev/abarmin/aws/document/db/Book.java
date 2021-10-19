package dev.abarmin.aws.document.db;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collation = "abarmin_books")
public class Book {
  @Id
  private ObjectId id;

  private String title;
  private String author;
}
