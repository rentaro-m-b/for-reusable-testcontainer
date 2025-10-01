package com.example.for_reusable_testcontainer.it.health;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.when;

public class HealthApiTest {
    @Test
    public void normal() {
        when().
            get("/health").
            then().
            statusCode(204);
    }
}
