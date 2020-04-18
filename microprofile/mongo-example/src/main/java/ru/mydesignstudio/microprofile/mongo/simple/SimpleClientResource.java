package ru.mydesignstudio.microprofile.mongo.simple;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/clients")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SimpleClientResource {
  @Inject
  SimpleClientService clientService;

  @GET
  @Path("")
  public List<SimpleClient> findAll() {
    return clientService.findAll();
  }

  @POST
  @Path("")
  public SimpleClient save(SimpleClient client) {
    return clientService.save(client);
  }
}
