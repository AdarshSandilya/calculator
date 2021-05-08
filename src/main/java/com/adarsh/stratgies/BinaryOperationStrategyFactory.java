package com.adarsh.stratgies;

import com.adarsh.models.ArithmeticOperator;

import java.util.HashMap;
import java.util.Map;

public class BinaryOperationStrategyFactory {
    private final Map<ArithmeticOperator, BinaryOperationStrategy> strategies = new HashMap<>();

    public BinaryOperationStrategyFactory() {
        strategies.put(ArithmeticOperator.ADD, new AdditionStrategy());
        strategies.put(ArithmeticOperator.SUBTRACT, new SubtractionStrategy());
        strategies.put(ArithmeticOperator.MULTIPLY, new MultiplicationStrategy());
        strategies.put(ArithmeticOperator.DIVIDE, new DivisionStrategy());
    }

    public BinaryOperationStrategy getStrategy(ArithmeticOperator operator) {
        return strategies.get(operator);
    }
}

