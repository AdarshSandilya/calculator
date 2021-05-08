package com.adarsh.models;

import java.util.Arrays;

public enum ArithmeticOperators {
    ADD('+', 1),
    SUBTRACT('-', 1),
    MULTIPLY('*', 2),
    DIVIDE('/', 2);

    private final Character operator;
    private final int precedence;

    ArithmeticOperators(Character operator, int precedence) {
        this.operator = operator;
        this.precedence = precedence;
    }

    public Character getOperator() {
        return operator;
    }

    public int getPrecedence() {
        return precedence;
    }
    
    public static boolean isAnOperator(char token){
        return Arrays.stream(ArithmeticOperators.values()).anyMatch(operator -> operator.getOperator() == token);
    }
    
    public boolean isPrecedenceOf(ArithmeticOperators operator){
         return this.getPrecedence() > operator.getPrecedence();
    }
    
}
