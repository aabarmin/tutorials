package ru.mydesignstudio.microprofile.mongo.repository.panache;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;

@RequestScoped
public class ClientService {
  @Inject
  ClientRepository clientRepository;

  public List<Client> findAll() {
    return clientRepository.findAll().list();
  }

  public Client save(Client client) {
    clientRepository.persistOrUpdate(client);
    return client;
  }
}
