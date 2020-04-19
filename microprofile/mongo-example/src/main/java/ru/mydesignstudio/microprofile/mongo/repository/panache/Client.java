package ru.mydesignstudio.microprofile.mongo.repository.panache;

import io.quarkus.mongodb.panache.MongoEntity;
import org.bson.types.ObjectId;

@MongoEntity(collection = "repository-client")
public class Client {
  public ObjectId id;
  public String name;
  public String description;
}
