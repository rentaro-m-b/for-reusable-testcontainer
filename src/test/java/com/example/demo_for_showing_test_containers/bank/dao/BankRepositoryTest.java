package com.example.demo_for_showing_test_containers.bank.dao;

import com.example.demo_for_showing_test_containers.bank.domain.Bank;
import com.example.demo_for_showing_test_containers.bank.domain.Currency;
import com.example.demo_for_showing_test_containers.bank.domain.Money;
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
class BankRepositoryTest {
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
    BankRepositoryImpl target;

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
                        new Money(100, Currency.YEN),
                        LocalDateTime.parse("2025-01-01T00:00:00")
                ),
                new Bank(
                        "00000000-0000-0000-0000-000000000002",
                        new Money(120, Currency.YEN),
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
                new Money(200, Currency.YEN),
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
    void updateBank() throws Exception {
        // setup

        // execute
        target.updateBank(
                new Bank(
                        "00000000-0000-0000-0000-000000000001",
                        new Money(300, Currency.DOLLAR),
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
    void deleteBank() throws Exception {
        // setup

        // execute
        target.deleteBank("00000000-0000-0000-0000-000000000001");

        // assert
    }
}
