package com.siddhartho.calculator;

import android.util.Log;

public abstract class CalculatorHelper {
    private static final String TAG = "CalculatorHelper";

    public static String pendingOperation = "";

    /**
     * if operand1 is null result = current value
     * if pending operation is empty or '=' or ('/' and current operation is '%') perform the current operation
     * if pending operation '=' new value = result
     * if pending operation '/' operand1/current value = result
     * if pending operation 'x' and -> current operation not '%' operand1*current value = result
     * -> current operation '%' result = 0
     * if pending operation '+' and -> current operation not '%' operand1+current value = result
     * -> current operation '%' result = 0
     * if pending operation '-' and -> current operation not '%' operand1-current value = result
     * -> current operation '%' result = 0
     * if pending operation '%' (operand1/current value)*100 = result
     *
     * @param operand1  = previous result stored (line 1)
     * @param value     = current value as operand2 (line 2)
     * @param operation = next operation
     * @return result
     */
    public static Double performOperation(Double operand1, Double value, String operation) {
        Log.d(TAG, "performOperation() called with: operand1 = [" + operand1 + "], value = [" + value + "], pendingOperation = [" + pendingOperation + "], operation = [" + operation + "]");
        if (operand1 == null) operand1 = value;
        else {
            if (pendingOperation.equals("") || pendingOperation.equals("=") || (operation.equals("%") && pendingOperation.equals("/")))
                pendingOperation = operation;

            switch (pendingOperation) {
                case "=":
                    operand1 = value;
                    break;
                case "/":
                    operand1 /= value;
                    break;
                case "x":
                    if (operation.equals("%")) operand1 = 0.0;
                    else operand1 *= value;
                    break;
                case "+":
                    if (operation.equals("%")) operand1 = 0.0;
                    else operand1 += value;
                    break;
                case "-":
                    if (operation.equals("%")) operand1 = 0.0;
                    else operand1 -= value;
                    break;
                case "%":
                    operand1 /= value;
                    operand1 *= 100;
                    break;
            }
        }
        return operand1;
    }
}
