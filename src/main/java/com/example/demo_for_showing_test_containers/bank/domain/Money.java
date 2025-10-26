package com.example.demo_for_showing_test_containers.bank.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;

public record Money(int deposit, Currency currency) {
    public Money add(Money addend) {
        if (currency != addend.currency) {
            var result = this.convertToDollar().deposit + addend.convertToDollar().deposit;
            result = BigDecimal.valueOf(result).multiply(currency.rate()).setScale(0, RoundingMode.HALF_UP).intValue();
            return new Money(result, currency);
        }
        return new Money(deposit + addend.deposit, currency);
    }

    public Money convertToDollar() {
        if (currency.isDollar()) {
            return this;
        }
        var convertedBigDecimal = BigDecimal.valueOf(deposit).divide(currency.rate(), RoundingMode.HALF_UP);
        var converted = convertedBigDecimal.setScale(0, RoundingMode.HALF_UP).intValue();
        System.out.println(converted);
        return new Money(converted, Currency.DOLLAR);
    }
}
