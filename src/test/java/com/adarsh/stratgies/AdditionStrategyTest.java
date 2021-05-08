package com.adarsh.stratgies;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AdditionStrategyTest {

    @Test
    void apply_should_add_two_numbers() {
        AdditionStrategy strategy = new AdditionStrategy();
        double res = strategy.apply(1, 2);
        assertEquals(3, res);
    }
}