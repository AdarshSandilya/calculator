package com.adarsh.models;

import com.adarsh.AppConstants;
import com.adarsh.exceptions.InvalidExpression;
import com.adarsh.stratgies.BinaryOperationStrategy;
import com.adarsh.stratgies.BinaryOperationStrategyFactory;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Stack;

public class MathematicalExpression {

    private final String expression;
    private final HashSet<Character> ALLOWED_TOKENS = new HashSet<>(Arrays.asList('0', '1', '2', '3', '4', '5', '6', '7',
            '8', '9', '+', '-', '/', '*', '(', ')', ' '));


    public MathematicalExpression(String expression) {
        this.expression = expression;
    }

    public boolean isValid() {
        int openBracesCount = 0;
        int closeBracesCount = 0;
        for (int i = 0; i < this.expression.length(); i++) {
            if (ALLOWED_TOKENS.contains(this.expression.charAt(i))) {
                if (this.expression.charAt(i) == '(')
                    openBracesCount++;
                if (this.expression.charAt(i) == ')')
                    closeBracesCount++;
            } else
                return false;
        }
        return openBracesCount == closeBracesCount;
    }

    public double eval() throws InvalidExpression {
        if (!isValid())
            throw new InvalidExpression(AppConstants.Message.INVALID_EXPRESSION);
        char[] tokens = expression.toCharArray();
        BinaryOperationStrategyFactory strategyFactory = new BinaryOperationStrategyFactory();
        Stack<Double> values = new Stack<>();
        Stack<ArithmeticOperator> operators = new Stack<>();
        boolean isParenthesisOpen = false;

        for (int i = 0; i < tokens.length; i++) {
            if (tokens[i] == ' ') continue;
            //push digits
            if (Character.isDigit(tokens[i])) {
                StringBuilder currentDigit = new StringBuilder();
                while (i < tokens.length && Character.isDigit(tokens[i]))
                    currentDigit.append(tokens[i++]);

                values.push(Double.valueOf(currentDigit.toString()));
                i--;
                continue;
            }
            ArithmeticOperator currentOperator = ArithmeticOperator.fromString(tokens[i]);
            BinaryOperationStrategy strategy;

            if (currentOperator == ArithmeticOperator.OPEN_PARENTHESIS) {
                operators.push(currentOperator);
                isParenthesisOpen = true;
            } else if (currentOperator == ArithmeticOperator.CLOSE_PARENTHESIS) {
                while (operators.peek() != ArithmeticOperator.OPEN_PARENTHESIS) {
                    strategy = strategyFactory.getStrategy(operators.pop());
                    values.push(strategy.apply(values.pop(), values.pop()));
                }
                operators.pop();
                isParenthesisOpen = false;
            } else if (ArithmeticOperator.isAnOperator(tokens[i])) {
                while (!operators.empty() && !isParenthesisOpen && operators.peek().isPrecedenceOf(currentOperator)) {
                    strategy = strategyFactory.getStrategy(operators.pop());
                    values.push(strategy.apply(values.pop(), values.pop()));
                }
                operators.push(currentOperator);

            }
        }
        while (!operators.empty()) {
            BinaryOperationStrategy strategy = strategyFactory.getStrategy(operators.pop());
            values.push(strategy.apply(values.pop(), values.pop()));
        }
        return values.pop();
    }
}