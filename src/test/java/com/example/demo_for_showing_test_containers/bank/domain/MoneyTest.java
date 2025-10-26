package com.example.demo_for_showing_test_containers.bank.domain;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MoneyTest {
    @Test
    void add() {
        // setup
        var augend = new Money(100, Currency.YEN);
        var addend = new Money(150, Currency.YEN);

        // execute
        var actual = augend.add(addend);

        // assert
        var expected = new Money(250, Currency.YEN);
        assertThat(actual).isEqualTo(expected);
    }
    
    @Test
    void convertToDollar() {
        // setup
        var value1 = new Money(150, Currency.YEN);
        var value2 = new Money(100, Currency.YEN);

        // execute
        var actual1 = value1.convertToDollar();
        var actual2 = value2.convertToDollar();

        // assert
        var expected1 = new Money(1, Currency.DOLLAR);
        var expected2 = new Money(1, Currency.DOLLAR);

        assertThat(actual1).isEqualTo(expected1);
        assertThat(actual2).isEqualTo(expected2);
    }

    @Test
    void differentPlus() {
        // setup
        var augend1 = new Money(100, Currency.YEN);
        var addend1 = new Money(2, Currency.DOLLAR);

        var augend2 = new Money(150, Currency.YEN);
        var addend2 = new Money(2, Currency.DOLLAR);

        // execute
        var actual1 = augend1.add(addend1);
        var actual2 = augend2.add(addend2);

        // assert
        var expected1 = new Money(400, Currency.YEN);
        var expected2 = new Money(450, Currency.YEN);

        assertThat(actual1).isEqualTo(expected1);
        assertThat(actual2).isEqualTo(expected2);
    }
}
