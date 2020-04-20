package ru.mydesignstudio.microprofile.metrics;

import org.eclipse.microprofile.metrics.MetricUnits;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Gauge;
import org.eclipse.microprofile.metrics.annotation.Timed;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Path("/clients")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ClientResource {
  private List<Client> clients = new ArrayList<>();

  @POST
  @Path("")
  @Counted(name = "clientsSaved", description = "How many clients were saved")
  @Timed(name = "clientsSaveTime", description = "How long it takes to save a client", unit = MetricUnits.MILLISECONDS)
  public Client save(Client client) {
    clients.add(client);
    return client;
  }

  @Gauge(name = "clientsCount", unit = MetricUnits.NONE, description = "How many clients are in the system")
  public int clientsNumber() {
    return clients.size();
  }
}
