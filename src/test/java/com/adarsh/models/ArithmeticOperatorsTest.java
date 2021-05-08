package com.adarsh.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ArithmeticOperatorsTest {

    @Test
    void isAnOperator_should_return_true_if_given_value_is_a_valid_arithmetic_operator() {
        assertTrue(ArithmeticOperators.isAnOperator(ArithmeticOperators.ADD.getOperator()));
        assertTrue(ArithmeticOperators.isAnOperator(ArithmeticOperators.SUBTRACT.getOperator()));
        assertTrue(ArithmeticOperators.isAnOperator(ArithmeticOperators.MULTIPLY.getOperator()));
        assertTrue(ArithmeticOperators.isAnOperator(ArithmeticOperators.DIVIDE.getOperator()));
    }

    @Test
    void isAnOperator_should_return_false_if_given_value_is_an_invalid_arithmetic_operator() {
        assertFalse(ArithmeticOperators.isAnOperator('2'));
        assertFalse(ArithmeticOperators.isAnOperator('.'));
        assertFalse(ArithmeticOperators.isAnOperator('a'));
    }

    @Test
    void isPrecedenceOf_should_return_true_if_precedence_is_higher_than_another_operator() {
        assertTrue(ArithmeticOperators.MULTIPLY.isPrecedenceOf(ArithmeticOperators.ADD));
        assertTrue(ArithmeticOperators.MULTIPLY.isPrecedenceOf(ArithmeticOperators.SUBTRACT));
        assertTrue(ArithmeticOperators.DIVIDE.isPrecedenceOf(ArithmeticOperators.ADD));
        assertTrue(ArithmeticOperators.DIVIDE.isPrecedenceOf(ArithmeticOperators.ADD));
    }

    @Test
    void isPrecedenceOf_should_return_false_if_precedence_is_lower_than_another_operator() {
        assertFalse(ArithmeticOperators.ADD.isPrecedenceOf(ArithmeticOperators.MULTIPLY));
        assertFalse(ArithmeticOperators.ADD.isPrecedenceOf(ArithmeticOperators.DIVIDE));
        
        assertFalse(ArithmeticOperators.SUBTRACT.isPrecedenceOf(ArithmeticOperators.MULTIPLY));
        assertFalse(ArithmeticOperators.SUBTRACT.isPrecedenceOf(ArithmeticOperators.DIVIDE));
    }

    @Test
    void isPrecedenceOf_should_return_false_if_precedence_is_equal_than_another_operator() {
        assertFalse(ArithmeticOperators.ADD.isPrecedenceOf(ArithmeticOperators.SUBTRACT));
        assertFalse(ArithmeticOperators.SUBTRACT.isPrecedenceOf(ArithmeticOperators.ADD));
        assertFalse(ArithmeticOperators.DIVIDE.isPrecedenceOf(ArithmeticOperators.MULTIPLY));
        assertFalse(ArithmeticOperators.MULTIPLY.isPrecedenceOf(ArithmeticOperators.DIVIDE));
    }
}