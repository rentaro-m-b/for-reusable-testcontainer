package com.example.demo_for_showing_test_containers.bank.domain;

import java.util.Objects;

public enum Currency {
    YEN("JPY"),
    DOLLAR("USD"),
    EURO("EUR");

    private final String code;

    Currency(String code) {
        this.code = code;
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
}
