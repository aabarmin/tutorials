package ru.mydesignstudio.microprofile.mongo.repository.panache;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@QuarkusTest
class ClientResourceTest {
  @Test
  void findAll_returnsStatusCodeOk() {
    given()
            .when()
            .get("/repository-clients")
            .then()
            .statusCode(200);
  }

  @Test
  void save_savesClient() {
    final Client client = new Client();
    client.name = "Name";
    client.description = "Description";

    given()
            .body(client)
            .contentType(ContentType.JSON)
            .when()
            .post("/repository-clients")
            .then()
            .statusCode(200)
            .body("name", is("Name"))
            .body("description", is("Description"))
            .body("id", is(not(emptyString())));
  }
}