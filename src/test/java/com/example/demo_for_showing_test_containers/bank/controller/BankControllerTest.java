package com.example.demo_for_showing_test_containers.bank.controller;

import com.example.demo_for_showing_test_containers.bank.domain.Bank;
import com.example.demo_for_showing_test_containers.bank.domain.Currency;
import com.example.demo_for_showing_test_containers.bank.domain.Money;
import com.example.demo_for_showing_test_containers.bank.usecase.GetBanksUseCase;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.mockMvc;
import static org.hamcrest.Matchers.emptyString;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.doReturn;

@WebMvcTest(BankController.class)
class BankControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    GetBanksUseCase getBanksUseCase;

    @BeforeEach
    void setup() {
        mockMvc(mockMvc);
    }

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
        ).when(getBanksUseCase).handle();

        // execute & assert
        given()
                .when()
                .get("/banks")
                .then()
                .statusCode(200)
                .body("banks[0].value", equalTo(100))
                .body("banks[1].value", equalTo(120));
    }
}
