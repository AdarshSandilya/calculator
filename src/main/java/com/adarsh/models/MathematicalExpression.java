package com.adarsh.models;

import com.adarsh.AppConstants;
import com.adarsh.exceptions.InvalidExpression;

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
        Stack<Integer> values = new Stack<>();
        Stack<ArithmeticOperators> operators = new Stack<>();

        for (int i = 0; i < tokens.length; i++) {
            if (tokens[i] == ' ') continue;

            //push digits
            if (Character.isDigit(tokens[i])) {
                StringBuilder currentDigit = new StringBuilder();
                while (i < tokens.length && Character.isDigit(tokens[i]))
                    currentDigit.append(tokens[i++]);

                values.push(Integer.parseInt(currentDigit.toString()));
                i--;
                continue;
            }

            ArithmeticOperators currentOperator = ArithmeticOperators.fromString(tokens[i]);

            //push open braces
            if (currentOperator == ArithmeticOperators.OPEN_PARENTHESIS)
                operators.push(currentOperator);

                //solve close braces
            else if (currentOperator == ArithmeticOperators.CLOSE_PARENTHESIS) {
                while (operators.peek() != ArithmeticOperators.OPEN_PARENTHESIS)
                    values.push(applyOperation(operators.pop(), values.pop(), values.pop()));
                operators.pop();
            }

            // Current token is an operator.
            else if (ArithmeticOperators.isAnOperator(tokens[i])) {
                while (!operators.empty() && operators.peek().isPrecedenceOf(currentOperator))
                    values.push(applyOperation(operators.pop(), values.pop(), values.pop()));

                operators.push(currentOperator);
            }
        }

        while (!operators.empty())
            values.push(applyOperation(operators.pop(), values.pop(), values.pop()));

        return values.pop();
    }


    public static int applyOperation(ArithmeticOperators op, int b, int a) {
        switch (op) {
            case ADD:
                return a + b;
            case SUBTRACT:
                return a - b;
            case MULTIPLY:
                return a * b;
            case DIVIDE:
                return a / b;
        }
        return 0;
    }
}