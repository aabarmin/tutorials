package ru.mydesignstudio.microprofile.mongo.simple;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@RequestScoped
public class SimpleClientService {
  @Inject
  MongoClient mongoClient;

  @ConfigProperty(name = "mongo.database.name")
  String databaseName;

  public List<SimpleClient> findAll() {
    final List<SimpleClient> clients = new ArrayList<>();
    try (final MongoCursor<Document> iterator = getClientsCollection().find().iterator()) {
      while (iterator.hasNext()) {
        final Document document = iterator.next();
        final SimpleClient client = new SimpleClient();
        client.setName(document.getString("name"));
        client.setDescription(document.getString("description"));
        clients.add(client);
      }
    }
    return clients;
  }

  public SimpleClient save(SimpleClient client) {
    final Document document = new Document()
            .append("name", client.getName())
            .append("description", client.getDescription());

    getClientsCollection().insertOne(document);

    return client;
  }

  private MongoCollection getClientsCollection() {
    return mongoClient.getDatabase(databaseName)
            .getCollection("clients");
  }
}
