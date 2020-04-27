package ru.mydesignstudio.microprofile.config;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class ConfigResourceTest {
  @Test
  void getFixedValue() {
    given()
            .when()
            .get("/config/fixed")
            .then()
            .statusCode(200)
            .body(is("property-fixed"));
  }

  @Test
  void getDefaultValue() {
    given()
            .when()
            .get("/config/default")
            .then()
            .statusCode(200)
            .body(is("property-default"));
  }

  @Test
  void getProgrammatically() {
    given()
            .when()
            .get("/config/programmatically")
            .then()
            .statusCode(200)
            .body(is("property-programmatically"));
  }

  @Test
  void getComplexProperty() {
    given()
            .when()
            .get("/config/complex")
            .then()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .body("name", is("property-name"))
            .body("description", is("property-description"));
  }

  @Test
  void getPropertyOverride() {
    given()
            .when()
            .get("/config/override")
            .then()
            .statusCode(200)
            .body(is("test-profile"));
  }
}