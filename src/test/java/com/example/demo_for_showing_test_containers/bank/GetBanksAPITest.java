package com.example.demo_for_showing_test_containers.bank;

import com.github.database.rider.core.api.configuration.DBUnit;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.SeedStrategy;
import com.github.database.rider.spring.api.DBRider;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.images.builder.ImageFromDockerfile;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.utility.DockerImageName;


import java.io.File;
import java.nio.file.Paths;
import java.time.Duration;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

@DBRider
@DBUnit(
        schema = "main",
        caseSensitiveTableNames = true
)
@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class GetBanksAPITest {
    @Container
    public static GenericContainer<?> app =
            new GenericContainer<>(
                    new ImageFromDockerfile()
                            .withDockerfile(new File("Dockerfile").toPath())
                            .withFileFromPath(".", Paths.get("."))
            )
                    .withExposedPorts(8080)
                    .waitingFor(
                            Wait.forHttp("/health")
                                    .forStatusCode(NO_CONTENT.value())
                                    .withStartupTimeout(Duration.ofSeconds(120))
                    );

    @BeforeAll
    static void setUp() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8080;
    }

    @Test
    @DataSet(
            value = "datasets/banks.yaml",
            strategy = SeedStrategy.CLEAN_INSERT,
            cleanBefore = true
    )
    void getBanks() {
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
