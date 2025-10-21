package com.example.demo_for_showing_test_containers.bank;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public record BankRow(String id, int value, String unit, LocalDateTime createdAt) {
    static List<Bank> toEntity(List<BankRow> bankRows) {
        return bankRows.stream().map(BankRow::toEntity).collect(Collectors.toList());
    }

    Bank toEntity() {
        return new Bank(
                id,
                value,
                unit,
                createdAt
        );
    }

    static BankRow of(Bank bank) {
        return new BankRow(
                bank.id(),
                bank.value(),
                bank.unit(),
                bank.createdAt()
        );
    }
}
