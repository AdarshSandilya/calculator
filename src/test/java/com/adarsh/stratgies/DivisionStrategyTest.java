package com.adarsh.stratgies;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DivisionStrategyTest {

    @Test
    void apply_should_divide_given_two_numbers() {
        DivisionStrategy strategy = new DivisionStrategy();
        assertEquals( 2, strategy.apply(2, 4));
        assertEquals( 0.5, strategy.apply(4, 2));
    }
}