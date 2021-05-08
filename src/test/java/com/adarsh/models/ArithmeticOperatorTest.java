package com.adarsh.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ArithmeticOperatorTest {

    @Test
    void isAnOperator_should_return_true_if_given_value_is_a_valid_arithmetic_operator() {
        assertTrue(ArithmeticOperator.isAnOperator(ArithmeticOperator.ADD.getOperator()));
        assertTrue(ArithmeticOperator.isAnOperator(ArithmeticOperator.SUBTRACT.getOperator()));
        assertTrue(ArithmeticOperator.isAnOperator(ArithmeticOperator.MULTIPLY.getOperator()));
        assertTrue(ArithmeticOperator.isAnOperator(ArithmeticOperator.DIVIDE.getOperator()));
    }

    @Test
    void isAnOperator_should_return_false_if_given_value_is_an_invalid_arithmetic_operator() {
        assertFalse(ArithmeticOperator.isAnOperator('2'));
        assertFalse(ArithmeticOperator.isAnOperator('.'));
        assertFalse(ArithmeticOperator.isAnOperator('a'));
    }

    @Test
    void isPrecedenceOf_should_return_true_if_precedence_is_higher_than_another_operator() {
        assertTrue(ArithmeticOperator.MULTIPLY.isPrecedenceOf(ArithmeticOperator.ADD));
        assertTrue(ArithmeticOperator.MULTIPLY.isPrecedenceOf(ArithmeticOperator.SUBTRACT));
        assertTrue(ArithmeticOperator.DIVIDE.isPrecedenceOf(ArithmeticOperator.ADD));
        assertTrue(ArithmeticOperator.DIVIDE.isPrecedenceOf(ArithmeticOperator.ADD));
    }

    @Test
    void isPrecedenceOf_should_return_false_if_precedence_is_lower_than_another_operator() {
        assertFalse(ArithmeticOperator.ADD.isPrecedenceOf(ArithmeticOperator.MULTIPLY));
        assertFalse(ArithmeticOperator.ADD.isPrecedenceOf(ArithmeticOperator.DIVIDE));
        
        assertFalse(ArithmeticOperator.SUBTRACT.isPrecedenceOf(ArithmeticOperator.MULTIPLY));
        assertFalse(ArithmeticOperator.SUBTRACT.isPrecedenceOf(ArithmeticOperator.DIVIDE));
    }

    @Test
    void isPrecedenceOf_should_return_false_if_precedence_is_equal_than_another_operator() {
        assertFalse(ArithmeticOperator.ADD.isPrecedenceOf(ArithmeticOperator.SUBTRACT));
        assertFalse(ArithmeticOperator.SUBTRACT.isPrecedenceOf(ArithmeticOperator.ADD));
        assertFalse(ArithmeticOperator.DIVIDE.isPrecedenceOf(ArithmeticOperator.MULTIPLY));
        assertFalse(ArithmeticOperator.MULTIPLY.isPrecedenceOf(ArithmeticOperator.DIVIDE));
    }
}