package ru.mydesignstudio.microprofile.mongo.complex.panache;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

@QuarkusTest
class ComplexPanacheClientResourceTest {
  @Test
  void findAll_returnsSuccessStatusCode() {
    given()
            .when()
            .get("/panache-client")
            .then()
            .statusCode(200);
  }

  @Test
  void save_returnsSaved() {
    final ComplexPanacheClient client = new ComplexPanacheClient();
    client.name = "Name";
    client.description = "Description";

    given()
            .body(client)
            .contentType(ContentType.JSON)
            .when()
            .post("/panache-client")
            .then()
            .statusCode(200)
            .body("name", is("Name"))
            .body("description", is("Description"));
  }
}