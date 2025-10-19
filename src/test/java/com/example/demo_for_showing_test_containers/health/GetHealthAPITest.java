package com.example.demo_for_showing_test_containers.health;

import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.images.builder.ImageFromDockerfile;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.nio.file.Path;
import java.time.Duration;

import static io.restassured.RestAssured.given;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class GetHealthAPITest {
    @Container
    public static GenericContainer<?> app =
            new GenericContainer<>(
                    new ImageFromDockerfile(".")
                            .withDockerfilePath("Dockerfile")
            )
                    .withExposedPorts(8080)
                    .waitingFor(Wait.forHttp("/health").forStatusCode(204));

    @BeforeAll
    static void setUp() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8080;
    }

    @Test void getHealth() {
        // given().get("/health").then().statusCode(NO_CONTENT.value());
        System.out.println("");
    }
}
