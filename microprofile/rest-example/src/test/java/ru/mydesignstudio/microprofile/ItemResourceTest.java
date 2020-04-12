package ru.mydesignstudio.microprofile;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.MediaType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.core.Is.is;

@QuarkusTest
class ItemResourceTest {
  @Test
  void check_itemsExist() {
    given()
            .when().get("/items")
            .then()
                .statusCode(is(200))
                .contentType(MediaType.TEXT_HTML)
                .body(containsString("Items"));
  }
}