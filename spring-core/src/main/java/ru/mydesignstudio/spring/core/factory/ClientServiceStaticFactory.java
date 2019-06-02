package ru.mydesignstudio.spring.core.factory;

public class ClientServiceStaticFactory {
  public static ClientService newClientService() {
    return new ClientService();
  }
}
