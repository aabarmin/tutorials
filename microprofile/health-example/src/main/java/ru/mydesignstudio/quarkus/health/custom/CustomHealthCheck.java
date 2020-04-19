package ru.mydesignstudio.quarkus.health.custom;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Readiness;

import javax.enterprise.context.ApplicationScoped;

@Readiness
@ApplicationScoped
public class CustomHealthCheck implements HealthCheck {
  @Override
  public HealthCheckResponse call() {
    return HealthCheckResponse.named("Custom health check")
            .up()
            .withData("Name", "Custom checker")
            .withData("Value", "Looks good")
            .build();
  }
}
