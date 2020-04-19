package ru.mydesignstudio.microprofile.mongo.complex.panache;

import io.quarkus.mongodb.panache.MongoEntity;
import io.quarkus.mongodb.panache.PanacheMongoEntity;

@MongoEntity(collection = "panache-clients")
public class ComplexPanacheClient extends PanacheMongoEntity {
  public String name;
  public String description;

  public static ComplexPanacheClient findByName(String name) {
    return find("name", name).firstResult();
  }
}
