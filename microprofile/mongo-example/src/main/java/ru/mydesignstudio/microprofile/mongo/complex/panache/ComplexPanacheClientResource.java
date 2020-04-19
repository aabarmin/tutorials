package ru.mydesignstudio.microprofile.mongo.complex.panache;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/panache-client")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ComplexPanacheClientResource {
  @Inject
  ComplexPanacheClientService clientService;

  @GET
  @Path("")
  public List<ComplexPanacheClient> findAll() {
    return clientService.findAll();
  }

  @POST
  @Path("")
  public ComplexPanacheClient save(ComplexPanacheClient client) {
    return clientService.save(client);
  }
}
