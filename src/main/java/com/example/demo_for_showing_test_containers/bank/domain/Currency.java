package com.example.demo_for_showing_test_containers.bank.domain;

import java.math.BigDecimal;
import java.util.Objects;

public enum Currency {
    YEN("JPY", BigDecimal.valueOf(150)),
    DOLLAR("USD", BigDecimal.valueOf(1)),
    EURO("EUR", BigDecimal.valueOf(1.1));

    private final String code;
    private final BigDecimal rate;

    Currency(String code, BigDecimal rate) {
        this.code = code;
        this.rate = rate;
    }

    public static Currency codeOf(String code) {
        for (Currency currency : values()) {
            if (Objects.equals(currency.code, code)) {
                return currency;
            }
        }
        throw new IllegalArgumentException(String.format("Unknown currency code: %s", code));
    }

    public String code() {
        return code;
    }

    public BigDecimal rate() {
        return rate;
    }

    public boolean isDollar() {
        return Objects.equals(code, Currency.DOLLAR.code());
    }
}
