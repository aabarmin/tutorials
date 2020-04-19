package ru.mydesignstudio.microprofile.mongo.repository.panache;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/repository-clients")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ClientResource {
  @Inject
  ClientService clientService;

  @GET
  @Path("")
  public List<Client> findAll() {
    return clientService.findAll();
  }

  @POST
  @Path("")
  public Client save(@Valid Client client) {
    return clientService.save(client);
  }
}
