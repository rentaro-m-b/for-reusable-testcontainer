package com.example.demo_for_showing_test_containers.bank;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public record BankRow(String id, int value, String unit, LocalDateTime createdAt) {
    static List<Bank> of(List<BankRow> bankRows) {
        return bankRows.stream().map(BankRow::of).collect(Collectors.toList());
    }

    Bank of() {
        return new Bank(
                id,
                value,
                unit,
                createdAt
        );
    }
}
