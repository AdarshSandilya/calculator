package com.adarsh.services;

import com.adarsh.AppConstants;
import com.adarsh.exceptions.InvalidExpression;
import com.adarsh.models.MathematicalExpression;

public class CalculatorService {
    private final MathematicalExpression expression;

    public CalculatorService(MathematicalExpression expression) {
        this.expression = expression;
    }

    public double evaluate() throws InvalidExpression {
        return expression.eval();
    }
}
