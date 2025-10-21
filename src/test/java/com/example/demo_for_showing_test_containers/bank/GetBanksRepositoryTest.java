package com.example.demo_for_showing_test_containers.bank;

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
class GetBanksRepositoryTest {
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
    BankRepository target;

    @Test
    @DataSet(
            value = "datasets/banks.yaml",
            strategy = SeedStrategy.CLEAN_INSERT,
            cleanBefore = true
    )
    void getBanks() throws Exception {
        // setup

        // execute
        List<Bank> actual = target.listBanks();

        // assert
        List<Bank> expected = List.of(
                new Bank(
                        "00000000-0000-0000-0000-000000000001",
                        100,
                        "JPY",
                        LocalDateTime.parse("2025-01-01T00:00:00")
                ),
                new Bank(
                        "00000000-0000-0000-0000-000000000002",
                        120,
                        "JPY",
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
    void createBank() throws Exception {
        // setup

        // execute
        target.createBank(new Bank(
                "00000000-0000-0000-0000-000000000003",
                200,
                "JPY",
                LocalDateTime.parse("2025-01-01T00:00:00")
        ));

        // assert
    }
}
