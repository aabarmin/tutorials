package ru.mydesignstudio.microprofile.mongo.simple;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
class SimpleClientResourceTest {
  @Inject
  SimpleClientResource clientResource;

  @Test
  void check_contextStarts() {
    assertNotNull(clientResource);
  }

  @Test
  void findAll_returnsStatusOk() {
    given()
            .when()
            .get("/clients")
            .then()
            .statusCode(200);
  }

  @Test
  void save_returnsSaved() {
    final SimpleClient body = new SimpleClient();
    body.setName("Name");
    body.setDescription("Description");

    given()
            .contentType(ContentType.JSON)
            .body(body)
            .when()
            .post("/clients")
            .then()
            .statusCode(200)
            .body("name", is("Name"))
            .body("description", is("Description"));

    // check something is returned
    given()
            .when()
            .get("/clients")
            .then()
            .statusCode(200)
            .body("$.size()", greaterThan(0));
  }
}