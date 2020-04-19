package ru.mydesignstudio.quarkus.health;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;

@QuarkusTest
class HealthCheckTest {
  @Test
  void check_liveness() {
    given()
            .when()
            .get("/health/live")
            .then()
            .statusCode(200)
            .body("status", is("UP"))
            .body("checks.size()", is(2))
            .body("checks.status", hasItems("UP", "UP"))
            .body("checks.name", hasItems("Works fine", "Another check"));
  }

  @Test
  void check_readiness() {
    given()
            .when()
            .get("/health/ready")
            .then()
            .statusCode(200)
            .body("status", is("UP"))
            .body("checks.size()", is(2))
            .body("checks.status", hasItems("UP", "UP"))
            .body("checks.data.Name", hasItem("Custom checker"))
            .body("checks.data.Value", hasItem("Looks good"));
  }
}