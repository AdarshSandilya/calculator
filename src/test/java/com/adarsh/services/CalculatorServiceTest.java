package com.adarsh.services;

import com.adarsh.AppConstants;
import com.adarsh.exceptions.InvalidExpression;
import com.adarsh.models.MathematicalExpression;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CalculatorServiceTest {
    @Mock
    private MathematicalExpression expression;
    @InjectMocks
    private CalculatorService service;
    
    @Test
    void evaluate_should_throw_exception_if_mathematical_expression_is_invalid() {
        when(expression.isValid()).thenReturn(false);
        
        InvalidExpression exception = assertThrows(InvalidExpression.class, () -> service.evaluate());
        assertEquals(AppConstants.Message.INVALID_EXPRESSION, exception.getMessage());
    }

}