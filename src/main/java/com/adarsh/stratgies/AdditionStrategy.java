package com.adarsh.stratgies;

public class AdditionStrategy implements BinaryOperationStrategy {
    @Override
    public double apply(double operand1, double operand2) {
        return operand1 + operand2;
    }
}
