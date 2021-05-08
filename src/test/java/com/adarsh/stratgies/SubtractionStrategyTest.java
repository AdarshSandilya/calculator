package com.adarsh.stratgies;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SubtractionStrategyTest {

    @Test
    void apply_should_subtract_given_number() {
        SubtractionStrategy strategy = new SubtractionStrategy();
        assertEquals(-2, strategy.apply(4, 2));
        assertEquals(2, strategy.apply(2, 4));
        assertEquals(-0.14000000000000012, strategy.apply(2.14, 2));
    }
}