package com.siddhartho.calculator;

import org.junit.Test;

import static org.junit.Assert.*;

public class CalculatorHelperTest {

    @Test
    public void operand1IsNullPerformCurrentOperation() {

        CalculatorHelper.pendingOperation = "";
        Double operand1 = null;
        Double currentValue = 5.0;
        String currentOperation = "=";
        Double expectedResult = 5.0;

        Double actualResult = CalculatorHelper.performOperation(operand1, currentValue, currentOperation);

        assertEquals(expectedResult, actualResult);
        assertNotEquals(currentOperation, CalculatorHelper.pendingOperation);
    }

    @Test
    public void pendingOperationIsEmptyPerformCurrentOperation() {

        CalculatorHelper.pendingOperation = "";
        Double operand1 = 2.0;
        Double currentValue = 5.0;
        String currentOperation = "+";
        Double expectedResult = 7.0;

        Double actualResult = CalculatorHelper.performOperation(operand1, currentValue, currentOperation);

        assertEquals(expectedResult, actualResult);
        assertEquals(currentOperation, CalculatorHelper.pendingOperation);
    }

    @Test
    public void pendingOperationIsEqualPerformCurrentOperation() {

        CalculatorHelper.pendingOperation = "=";
        Double operand1 = 2.0;
        Double currentValue = 5.0;
        String currentOperation = "x";
        Double expectedResult = 10.0;

        Double actualResult = CalculatorHelper.performOperation(operand1, currentValue, currentOperation);

        assertEquals(expectedResult, actualResult);
        assertEquals(currentOperation, CalculatorHelper.pendingOperation);
    }

    @Test
    public void pendingOperationIsDivisionCurrentOperationIsPercentPerformCurrentOperation() {

        CalculatorHelper.pendingOperation = "/";
        Double operand1 = 2.0;
        Double currentValue = 5.0;
        String currentOperation = "%";
        Double expectedResult = 40.0;

        Double actualResult = CalculatorHelper.performOperation(operand1, currentValue, currentOperation);

        assertEquals(expectedResult, actualResult);
        assertEquals(currentOperation, CalculatorHelper.pendingOperation);
    }

    @Test
    public void pendingOperationIsDivisionPerformCurrentOperation() {

        CalculatorHelper.pendingOperation = "/";
        Double operand1 = 2.0;
        Double currentValue = 5.0;
        String currentOperation = "+";
        Double expectedResult = 0.4;

        Double actualResult = CalculatorHelper.performOperation(operand1, currentValue, currentOperation);

        assertEquals(expectedResult, actualResult);
        assertNotEquals(currentOperation, CalculatorHelper.pendingOperation);
    }

    @Test
    public void pendingOperationIsMultiplyPerformCurrentOperation() {

        CalculatorHelper.pendingOperation = "x";
        Double operand1 = 2.0;
        Double currentValue = 5.0;
        String currentOperation = "+";
        Double expectedResult = 10.0;

        Double actualResult = CalculatorHelper.performOperation(operand1, currentValue, currentOperation);

        assertEquals(expectedResult, actualResult);
        assertNotEquals(currentOperation, CalculatorHelper.pendingOperation);
    }

    @Test
    public void pendingOperationIsMultiplyCurrentOperationIsPercentPerformCurrentOperation() {

        CalculatorHelper.pendingOperation = "x";
        Double operand1 = 2.0;
        Double currentValue = 5.0;
        String currentOperation = "%";
        Double expectedResult = 0.0;

        Double actualResult = CalculatorHelper.performOperation(operand1, currentValue, currentOperation);

        assertEquals(expectedResult, actualResult);
        assertNotEquals(currentOperation, CalculatorHelper.pendingOperation);
    }

    @Test
    public void pendingOperationIsAdditionPerformCurrentOperation() {

        CalculatorHelper.pendingOperation = "+";
        Double operand1 = 2.0;
        Double currentValue = 5.0;
        String currentOperation = "-";
        Double expectedResult = 7.0;

        Double actualResult = CalculatorHelper.performOperation(operand1, currentValue, currentOperation);

        assertEquals(expectedResult, actualResult);
        assertNotEquals(currentOperation, CalculatorHelper.pendingOperation);
    }

    @Test
    public void pendingOperationIsAdditionCurrentOperationIsPercentPerformCurrentOperation() {

        CalculatorHelper.pendingOperation = "+";
        Double operand1 = 2.0;
        Double currentValue = 5.0;
        String currentOperation = "%";
        Double expectedResult = 0.0;

        Double actualResult = CalculatorHelper.performOperation(operand1, currentValue, currentOperation);

        assertEquals(expectedResult, actualResult);
        assertNotEquals(currentOperation, CalculatorHelper.pendingOperation);
    }

    @Test
    public void pendingOperationIsSubtractionPerformCurrentOperation() {

        CalculatorHelper.pendingOperation = "-";
        Double operand1 = 2.0;
        Double currentValue = 5.0;
        String currentOperation = "+";
        Double expectedResult = -3.0;

        Double actualResult = CalculatorHelper.performOperation(operand1, currentValue, currentOperation);

        assertEquals(expectedResult, actualResult);
        assertNotEquals(currentOperation, CalculatorHelper.pendingOperation);
    }

    @Test
    public void pendingOperationIsSubtractionCurrentOperationIsPercentPerformCurrentOperation() {

        CalculatorHelper.pendingOperation = "-";
        Double operand1 = 2.0;
        Double currentValue = 5.0;
        String currentOperation = "%";
        Double expectedResult = 0.0;

        Double actualResult = CalculatorHelper.performOperation(operand1, currentValue, currentOperation);

        assertEquals(expectedResult, actualResult);
        assertNotEquals(currentOperation, CalculatorHelper.pendingOperation);
    }

    @Test
    public void pendingOperationIsPercentPerformCurrentOperation() {

        CalculatorHelper.pendingOperation = "%";
        Double operand1 = 2.0;
        Double currentValue = 5.0;
        String currentOperation = "+";
        Double expectedResult = 40.0;

        Double actualResult = CalculatorHelper.performOperation(operand1, currentValue, currentOperation);

        assertEquals(expectedResult, actualResult);
        assertNotEquals(currentOperation, CalculatorHelper.pendingOperation);
    }
}