package ru.mydesignstudio.microprofile.metrics;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasKey;

@QuarkusTest
class ClientResourceTest {
  @Test
  void metrics_getBaseMetrics() {
    given()
            .accept(ContentType.JSON)
            .when()
            .get("/metrics")
            .then()
            .body("$", hasKey("base"))
            .body("$", hasKey("vendor"))
            .body("$", hasKey("application"));
  }

  @Test
  void metrics_getApplicationMetrics() {
    final Client client = new Client();
    client.name = "Name";
    client.description = "Description";

    given()
            .contentType(ContentType.JSON)
            .body(client)
            .when()
            .post("/clients")
            .then()
            .statusCode(200);

    given()
            .accept(ContentType.JSON)
            .when()
            .get("/metrics/application")
            .then()
            .body("$", hasKey("ru.mydesignstudio.microprofile.metrics.ClientResource.clientsSaved"))
            .body("$", hasKey("ru.mydesignstudio.microprofile.metrics.ClientResource.clientsCount"))
            .body("$", hasKey("ru.mydesignstudio.microprofile.metrics.ClientResource.clientsSaveTime"));
  }
}