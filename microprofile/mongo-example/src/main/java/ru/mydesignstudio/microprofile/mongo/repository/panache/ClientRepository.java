package ru.mydesignstudio.microprofile.mongo.repository.panache;

import io.quarkus.mongodb.panache.PanacheMongoRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ClientRepository implements PanacheMongoRepository<Client> {
}
