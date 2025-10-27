package com.example.demo_for_showing_test_containers.bank.dao;

import com.example.demo_for_showing_test_containers.bank.domain.Currency;
import com.example.demo_for_showing_test_containers.bank.domain.Money;
import com.example.demo_for_showing_test_containers.bank.domain.Bank;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public record BankRow(String id, BigDecimal deposit, String currency, LocalDateTime createdAt) {
    static List<Bank> toEntity(List<BankRow> bankRows) {
        return bankRows.stream().map(BankRow::toEntity).collect(Collectors.toList());
    }

    Bank toEntity() {
        return new Bank(id, new Money(deposit, Currency.codeOf(currency)), createdAt);
    }

    static BankRow of(Bank bank) {
        return new BankRow(
                bank.id(),
                bank.money().deposit(),
                bank.money().currency().code(),
                bank.createdAt()
        );
    }
}
