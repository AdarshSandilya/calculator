package com.adarsh.models;

import java.util.Arrays;

public enum ArithmeticOperator {
    ADD('+', 1),
    SUBTRACT('-', 1),
    MULTIPLY('*', 2),
    DIVIDE('/', 2),
    OPEN_PARENTHESIS('(', 3),
    CLOSE_PARENTHESIS(')', 3);

    private final Character operator;
    private final int precedence;

    ArithmeticOperator(Character operator, int precedence) {
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
        return Arrays.stream(ArithmeticOperator.values()).anyMatch(operator -> operator.getOperator() == token);
    }
    
    public boolean isPrecedenceOf(ArithmeticOperator operator){
         return this.getPrecedence() > operator.getPrecedence();
    }

    static public ArithmeticOperator fromString(char value) {
        return Arrays.stream(ArithmeticOperator.values()).filter(operator -> operator.getOperator() == value).findFirst().orElse(null);
    }
    
}
