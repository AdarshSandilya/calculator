package com.adarsh.models;

import com.adarsh.AppConstants;
import com.adarsh.exceptions.InvalidExpression;
import com.adarsh.stratgies.BinaryOperationStrategy;
import com.adarsh.stratgies.BinaryOperationStrategyFactory;

import java.util.Stack;

public class MathematicalExpression {

    private final String expression;

    public MathematicalExpression(String expression) {
        this.expression = expression;
    }

    public boolean isValid() {
        int openBracesCount = 0;
        int closeBracesCount = 0;
        for (int i = 0; i < this.expression.length(); i++) {
            char currentToken = this.expression.charAt(i);
            if (Character.isWhitespace(currentToken) || Character.isDigit(currentToken) || ArithmeticOperator.isAnOperator(currentToken)) {
                if (currentToken == '(')
                    openBracesCount++;
                else if (currentToken == ')')
                    closeBracesCount++;
            } else
                return false;
        }
        return openBracesCount == closeBracesCount;
    }

    public double eval() throws InvalidExpression {
        if (!isValid())
            throw new InvalidExpression(AppConstants.Message.INVALID_EXPRESSION);
        Stack<Double> values = new Stack<>();
        Stack<ArithmeticOperator> operators = new Stack<>();
        boolean isParenthesisOpen = false;

        for (int i = 0; i < expression.length(); i++) {
            char currentToken = expression.charAt(i);
            if (Character.isWhitespace(currentToken)) continue;

            if (Character.isDigit(currentToken)) {
                i = pushNumbers(expression, values, i);
                i--;
                continue;
            }
            ArithmeticOperator currentOperator = ArithmeticOperator.fromString(currentToken);

            if (currentOperator == ArithmeticOperator.OPEN_PARENTHESIS) {
                operators.push(currentOperator);
                isParenthesisOpen = true;
            } else if (currentOperator == ArithmeticOperator.CLOSE_PARENTHESIS) {
                evaluateBracketExpression(values, operators);
                operators.pop();
                isParenthesisOpen = false;
            } else if (ArithmeticOperator.isAnOperator(currentToken)) {
                if (!isParenthesisOpen) handleOperatorPrecedence(values, operators, currentOperator);
                operators.push(currentOperator);
            }
        }
        calculateRemainingOperations(values, operators);
        return values.pop();
    }

    private void calculateRemainingOperations(Stack<Double> values, Stack<ArithmeticOperator> operators) {
        BinaryOperationStrategyFactory strategyFactory = new BinaryOperationStrategyFactory();
        while (!operators.empty()) {
            BinaryOperationStrategy strategy = strategyFactory.getStrategy(operators.pop());
            values.push(strategy.apply(values.pop(), values.pop()));
        }
    }

    private void handleOperatorPrecedence(Stack<Double> values, Stack<ArithmeticOperator> operators, ArithmeticOperator currentOperator) {
        BinaryOperationStrategyFactory strategyFactory = new BinaryOperationStrategyFactory();
        while (!operators.empty() && operators.peek().isPrecedenceOf(currentOperator)) {
            BinaryOperationStrategy strategy = strategyFactory.getStrategy(operators.pop());
            values.push(strategy.apply(values.pop(), values.pop()));
        }
    }

    private void evaluateBracketExpression(Stack<Double> values, Stack<ArithmeticOperator> operators) {
        BinaryOperationStrategyFactory strategyFactory = new BinaryOperationStrategyFactory();
        while (operators.peek() != ArithmeticOperator.OPEN_PARENTHESIS) {
            BinaryOperationStrategy strategy = strategyFactory.getStrategy(operators.pop());
            values.push(strategy.apply(values.pop(), values.pop()));
        }
    }

    private int pushNumbers(String expression, Stack<Double> values, int currentIndex) {
        StringBuilder currentDigit = new StringBuilder();
        while (currentIndex < expression.length() && Character.isDigit(expression.charAt(currentIndex)))
            currentDigit.append(expression.charAt(currentIndex++));
        values.push(Double.valueOf(currentDigit.toString()));
        return currentIndex;
    }
}