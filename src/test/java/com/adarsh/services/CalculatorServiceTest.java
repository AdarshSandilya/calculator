package com.adarsh.services;

import com.adarsh.exceptions.InvalidExpression;
import com.adarsh.models.MathematicalExpression;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CalculatorServiceTest {
    @Mock
    private MathematicalExpression expression;
    @InjectMocks
    private CalculatorService service;

    @Test
    void evaluate_should_throw_exception_if_mathematical_expression_is_invalid() throws InvalidExpression {
        when(expression.eval()).thenThrow(InvalidExpression.class);

        assertThrows(InvalidExpression.class, () -> service.evaluate());
    }

    @Test
    void evaluate_should_call_mathematical_expression_to_evaluate_the_input() throws InvalidExpression {
        when(expression.eval()).thenReturn(5.0);

        Double res = assertDoesNotThrow(() -> service.evaluate());

        verify(expression, times(1)).eval();
        assertEquals(5.0, res);
    }

}