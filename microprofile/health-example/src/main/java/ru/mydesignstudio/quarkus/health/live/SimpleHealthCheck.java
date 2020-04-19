package ru.mydesignstudio.quarkus.health.live;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;

import javax.enterprise.context.ApplicationScoped;

@Liveness
@ApplicationScoped
public class SimpleHealthCheck implements HealthCheck {
  @Override
  public HealthCheckResponse call() {
    return HealthCheckResponse.up("Works fine");
  }
}
