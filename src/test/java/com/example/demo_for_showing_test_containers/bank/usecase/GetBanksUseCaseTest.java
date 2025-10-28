package com.example.demo_for_showing_test_containers.bank.usecase;

import com.example.demo_for_showing_test_containers.bank.domain.Bank;
import com.example.demo_for_showing_test_containers.bank.domain.BankRepository;
import com.example.demo_for_showing_test_containers.bank.domain.Currency;
import com.example.demo_for_showing_test_containers.bank.domain.Money;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;

@SpringBootTest
class GetBanksUseCaseTest {
    @Autowired
    GetBanksUseCase target;

    @MockitoBean
    BankRepository bankRepository;

    @Test
    void getBanks() throws Exception {
        // setup
        doReturn(
                List.of(
                        new Bank(
                                "00000000-0000-0000-0000-000000000001",
                                new Money(BigDecimal.valueOf(100), Currency.YEN),
                                LocalDateTime.parse("2025-01-01T00:00:00")
                        ),
                        new Bank(
                                "00000000-0000-0000-0000-000000000002",
                                new Money(BigDecimal.valueOf(120), Currency.YEN),
                                LocalDateTime.parse("2025-01-01T00:00:00")
                        )
                )
        ).when(bankRepository).listBanks();

        // execute
        List<Bank> actual = target.handle();

        // assert
        List<Bank> expected = List.of(
                new Bank(
                        "00000000-0000-0000-0000-000000000001",
                        new Money(BigDecimal.valueOf(100), Currency.YEN),
                        LocalDateTime.parse("2025-01-01T00:00:00")
                ),
                new Bank(
                        "00000000-0000-0000-0000-000000000002",
                        new Money(BigDecimal.valueOf(120), Currency.YEN),
                        LocalDateTime.parse("2025-01-01T00:00:00")
                )
        );
        assertThat(actual).isEqualTo(expected);
    }
}
