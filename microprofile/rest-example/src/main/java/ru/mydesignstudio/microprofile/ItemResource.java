package ru.mydesignstudio.microprofile;

import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import io.quarkus.qute.api.ResourcePath;
import ru.mydesignstudio.microprofile.service.ItemService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/items")
public class ItemResource {
  @Inject
  ItemService itemService;

  @Inject
  @ResourcePath("list.html")
  Template listTemplate;

  @GET
  @Path("")
  @Produces(MediaType.TEXT_HTML)
  public TemplateInstance viewAll() {
    return listTemplate.data("items", itemService.findAll());
  }
}
