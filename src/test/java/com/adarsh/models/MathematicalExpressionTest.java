package com.adarsh.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MathematicalExpressionTest {

    @Test
    void isValidExpression_should_return_true_if_mathematical_expression_is_valid() {
        String expression = "2+3 +4   ";
        MathematicalExpression expressionParser = new MathematicalExpression(expression);
        assertTrue(expressionParser::isValid);
    }

    @Test
    void isValidExpression_should_return_false_if_mathematical_expression_is_invalid() {
        String expression = "2+((3+4)";
        MathematicalExpression expressionParser = new MathematicalExpression(expression);
        assertFalse(expressionParser::isValid);
    }
}