package com.example.demo_for_showing_test_containers.company.dao;

import com.example.demo_for_showing_test_containers.bank.domain.*;
import com.github.database.rider.core.api.configuration.DBUnit;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import com.github.database.rider.core.api.dataset.SeedStrategy;
import com.github.database.rider.junit5.api.DBRider;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Testcontainers
@DBRider
@DBUnit(caseSensitiveTableNames = true)
class CompanyRepositoryTest {
    @ServiceConnection
    @Container
    static MySQLContainer<?> container =
            new MySQLContainer<>(
                DockerImageName.parse("mysql:8.4"))
                .withEnv("TZ", "UTC")
                .withReuse(false)
                .waitingFor(Wait.forLogMessage(".*ready for connections. Version:.*\\n", 1)
            );

    @BeforeAll
    static void setUpAll() {
        var flyway =
                Flyway
                        .configure()
                        .cleanDisabled(false)
                        .locations("classpath:db/migration")
                        .dataSource(
                                container.getJdbcUrl(),
                                container.getUsername(),
                                container.getPassword()
                        ).load();
        flyway.clean();
        flyway.migrate();
    }

    @Autowired
    CompanyRepository target;

    @Test
    @DataSet(
            value = "datasets/banks.yaml",
            strategy = SeedStrategy.CLEAN_INSERT,
            cleanBefore = true
    )
    void listCompanies() throws Exception {
        // setup

        // execute
        List<Company> actual = target.listCompanies();

        // assert
        List<Company> expected = List.of(
                new Company(
                        "00000000-0000-0000-0000-000000000001",
                        "Apple Inc.",
                        LocalDateTime.parse("2025-01-01T00:00:00")
                ),
                new Company(
                        "00000000-0000-0000-0000-000000000002",
                        "Oracle",
                        LocalDateTime.parse("2025-01-01T00:00:00")
                )
        );
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DataSet(
            value = "datasets/banks.yaml",
            strategy = SeedStrategy.CLEAN_INSERT,
            cleanBefore = true
    )
    @ExpectedDataSet(
            value = "expected/createBank.yaml"
    )
    void createCompany() throws Exception {
        // setup

        // execute
        target.createCompany(new Company(
                "00000000-0000-0000-0000-000000000003",
                "forest",
                LocalDateTime.parse("2025-01-01T00:00:00")
        ));

        // assert
    }

    @Test
    @DataSet(
            value = "datasets/banks.yaml",
            strategy = SeedStrategy.CLEAN_INSERT,
            cleanBefore = true
    )
    @ExpectedDataSet(
            value = "expected/updateBank.yaml"
    )
    void updateCompany() throws Exception {
        // setup

        // execute
        target.updateCompany(
                new Company(
                        "00000000-0000-0000-0000-000000000001",
                        "Lisa Inc.",
                        LocalDateTime.parse("2025-01-01T00:00:01")
                )
        );

        // assert
    }

    @Test
    @DataSet(
            value = "datasets/banks.yaml",
            strategy = SeedStrategy.CLEAN_INSERT,
            cleanBefore = true
    )
    @ExpectedDataSet(
            value = "expected/deleteBank.yaml"
    )
    void deleteCompany() throws Exception {
        // setup

        // execute
        target.deleteCompany("00000000-0000-0000-0000-000000000001");

        // assert
    }
}
