package ru.mydesignstudio.microprofile.mongo.complex.panache;

import javax.enterprise.context.RequestScoped;
import java.util.List;

@RequestScoped
public class ComplexPanacheClientService {
  public List<ComplexPanacheClient> findAll() {
    return ComplexPanacheClient.findAll().list();
  }

  public ComplexPanacheClient save(ComplexPanacheClient client) {
    client.persist();
    return client;
  }
}
