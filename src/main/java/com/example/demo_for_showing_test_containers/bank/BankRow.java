package com.example.demo_for_showing_test_containers.bank;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public record BankRow(String id, int deposit, String unit, LocalDateTime createdAt) {
    static List<Bank> toEntity(List<BankRow> bankRows) {
        return bankRows.stream().map(BankRow::toEntity).collect(Collectors.toList());
    }

    Bank toEntity() {
        return new Bank(
                id,
                deposit,
                unit,
                createdAt
        );
    }

    static BankRow of(Bank bank) {
        return new BankRow(
                bank.id(),
                bank.deposit(),
                bank.unit(),
                bank.createdAt()
        );
    }
}
