package com.adarsh.stratgies;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MultiplicationStrategyTest {

    @Test
    void apply_should_multiply_given_two_numbers() {
        MultiplicationStrategy strategy = new MultiplicationStrategy();
        assertEquals(8, strategy.apply(2, 4));
        assertEquals(8.16, strategy.apply(2.4, 3.4));
    }
}