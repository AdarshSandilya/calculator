package com.adarsh.models;

import com.adarsh.AppConstants;
import com.adarsh.exceptions.InvalidExpression;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MathematicalExpressionTest {

    @Test
    void isValidExpression_should_return_true_if_mathematical_expression_is_valid() {
        String expression = "2+3 +4   ";
        MathematicalExpression mathematicalExpression = new MathematicalExpression(expression);
        assertTrue(mathematicalExpression::isValid);
    }

    @Test
    void isValidExpression_should_return_false_if_mathematical_expression_is_invalid() {
        String expression = "2+((3+4)";
        MathematicalExpression mathematicalExpression = new MathematicalExpression(expression);
        assertFalse(mathematicalExpression::isValid);
    }

    @Test
    void eval_should_throw_exception_if_mathematical_expression_is_invalid() {
        String expression = "2+((3+4)";
        MathematicalExpression mathematicalExpression = new MathematicalExpression(expression);
        InvalidExpression exception = assertThrows(InvalidExpression.class, mathematicalExpression::eval);
        assertEquals(AppConstants.Message.INVALID_EXPRESSION, exception.getMessage());
    }


    @Test
    void eval_should_evaluate_the_expression_if_given_expression_is_valid() {
        String expression = "2*3+4";
        MathematicalExpression mathematicalExpression = new MathematicalExpression(expression);
        Double res = assertDoesNotThrow(mathematicalExpression::eval);
        assertEquals(10, res);
    }

    @Test
    void eval_should_handle_the_bodmas_rules_for_a_valid_expression() {
        String expression = "5 / 2 - 3";
        MathematicalExpression mathematicalExpression = new MathematicalExpression(expression);
        Double res = assertDoesNotThrow(mathematicalExpression::eval);
        assertEquals(-0.5, res);
    }

    @Test
    void eval_should_handle_the_brackets_for_a_valid_expression() {
        String expression = "5 / (2 - 3)";
        MathematicalExpression mathematicalExpression = new MathematicalExpression(expression);
        Double res = assertDoesNotThrow(mathematicalExpression::eval);
        assertEquals(-5, res);
    }
}