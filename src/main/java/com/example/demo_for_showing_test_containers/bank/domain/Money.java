package com.example.demo_for_showing_test_containers.bank.domain;

public record Money(int deposit, Currency currency) {
    public Money plus(Money addend) {
        if (currency != addend.currency) {
            // TODO: augend base で計算をする
            // YEN : DOLLAR = 100 : 1 としたら？
            // DOLLAR : EURO = 1 : 1.1 としたら？
            // YEN : EURO = 100 * 1.1 : 1
            // 100は何？YENの割合。相手がYENだったらこれは可能だが、相手がEURだったら？
            if (addend.currency == Currency.DOLLAR) {
                var converted = addend.deposit * 100;
                return new Money(deposit + converted, currency);
            }
            if (addend.currency == Currency.EURO) {
                var converted = addend.deposit * 110;
                return new Money(deposit + converted, currency);
            }
        }
        return new Money(deposit + addend.deposit, currency);
    }
}
