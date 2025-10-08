package com.example.demo_for_showing_test_containers.bank;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.http.HttpStatus.OK;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class GetBanksAPITest {
    @BeforeAll
    static void setUp() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8080;
    }

    @Test void getBanks() {
        given()
                .get("/banks")
        .then()
                .statusCode(OK.value())
                .body("banks.size()", equalTo(2))
                .body("banks[0].value", equalTo(100))
                .body("banks[1].value", equalTo(120))
        ;
    }
}
