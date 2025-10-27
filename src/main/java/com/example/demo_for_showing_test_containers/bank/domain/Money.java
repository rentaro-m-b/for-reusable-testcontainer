package com.example.demo_for_showing_test_containers.bank.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;

public record Money(BigDecimal deposit, Currency currency) {
    public Money add(Money addend) {
        if (currency != addend.currency) {
            var result = this.convertToDollar().deposit.add(addend.convertToDollar().deposit);
            result = result.multiply(currency.rate()).setScale(2, RoundingMode.HALF_UP);
            return new Money(result, currency);
        }
        return new Money(deposit.add(addend.deposit), currency);
    }

    public Money convertToDollar() {
        if (currency.isDollar()) {
            return this;
        }
        var converted = deposit.divide(currency.rate(), 2, RoundingMode.HALF_UP);
        return new Money(converted, Currency.DOLLAR);
    }
}
