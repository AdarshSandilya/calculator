package com.adarsh.models;

import com.adarsh.AppConstants;
import com.adarsh.exceptions.InvalidExpression;

import java.util.Arrays;
import java.util.HashSet;

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
}