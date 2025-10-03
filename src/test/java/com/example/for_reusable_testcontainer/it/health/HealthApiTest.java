package com.example.for_reusable_testcontainer.it.health;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static io.restassured.RestAssured.when;
import static org.apache.catalina.util.XMLWriter.NO_CONTENT;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class HealthApiTest {
    @BeforeEach
    public void beforeEach() {
        RestAssured.baseURI = "http://localhost/api";
    }

    @Test
    public void normal() {
        when().
                get("/health").
                then().
                statusCode(NO_CONTENT)
        ;
    }
}
