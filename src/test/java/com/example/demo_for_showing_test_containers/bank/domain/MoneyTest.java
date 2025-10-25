package com.example.demo_for_showing_test_containers.bank.domain;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MoneyTest {
    @Test
    void plus() {
        // setup
        var augend = new Money(100, Currency.YEN);
        var addend = new Money(150, Currency.YEN);

        // execute
        var actual = augend.plus(addend);

        // assert
        var expected = new Money(250, Currency.YEN);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void differentPlus() {
        // setup
        var augend = new Money(100, Currency.YEN);
        var addend1 = new Money(2, Currency.DOLLAR);
        var addend2 = new Money(2, Currency.EURO);

        // execute
        var actual1 = augend.plus(addend1);
        var actual2 = augend.plus(addend2);

        // assert
        var expected1 = new Money(300, Currency.YEN);
        var expected2 = new Money(320, Currency.YEN);
        assertThat(actual1).isEqualTo(expected1);
        assertThat(actual2).isEqualTo(expected2);
    }
}
