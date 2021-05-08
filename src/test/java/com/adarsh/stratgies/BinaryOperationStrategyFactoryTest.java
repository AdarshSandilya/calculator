package com.adarsh.stratgies;

import com.adarsh.models.ArithmeticOperator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BinaryOperationStrategyFactoryTest {
    @Test
    void getStrategy_should_return_right_strategy() {
        BinaryOperationStrategyFactory factory = new BinaryOperationStrategyFactory();
        BinaryOperationStrategy strategy = factory.getStrategy(ArithmeticOperator.ADD);
        
        assertTrue(strategy instanceof AdditionStrategy);
    }
}