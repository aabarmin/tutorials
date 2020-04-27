package ru.mydesignstudio.microprofile.config;

import org.eclipse.microprofile.config.ConfigProvider;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/config")
@Produces(MediaType.TEXT_PLAIN)
public class ConfigResource {
  @ConfigProperty(name = "property.fixed")
  String propertyFixed;

  @ConfigProperty(name = "property.default", defaultValue = "property-default")
  String propertyDefault;

  @Inject
  ComplexProperty complexProperty;

  @ConfigProperty(name = "property.override")
  String propertyOverride;

  @GET
  @Path("/fixed")
  public String getFixedValue() {
    return propertyFixed;
  }

  @GET
  @Path("/default")
  public String getDefaultValue() {
    return propertyDefault;
  }

  @GET
  @Path("/programmatically")
  public String getProgrammatically() {
    return ConfigProvider.getConfig().getValue("property.programmatically", String.class);
  }

  @GET
  @Path("/override")
  public String getPropertyOverride() {
    return propertyOverride;
  }

  @GET
  @Path("/complex")
  @Produces(MediaType.APPLICATION_JSON)
  public ComplexProperty getComplexProperty() {
    return complexProperty;
  }
}
