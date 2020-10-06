package com.siddhartho.calculator;

import androidx.appcompat.app.AppCompatActivity;

import androidx.annotation.NonNull;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.siddhartho.calculator.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private ActivityMainBinding binding;

    private Double operand1 = null;
    private String pendingOperation = "";

    private static final String STATE_PENDING_OPERATION = "pendingOperation";
    private static final String STATE_OPERAND1 = "operand1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Log.d(TAG, "onCreate() called with: savedInstanceState = [" + savedInstanceState + "]");

        setNumericOnClickListener();

        setOperationOnClickListener();

        setFunctionOnClickListener();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Log.d(TAG, "onSaveInstanceState() called with: outState = [" + outState + "]");
        outState.putString(STATE_PENDING_OPERATION, pendingOperation);
        if (operand1 != null) outState.putDouble(STATE_OPERAND1, operand1);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        Log.d(TAG, "onRestoreInstanceState() called with: savedInstanceState = [" + savedInstanceState + "]");
        super.onRestoreInstanceState(savedInstanceState);
        pendingOperation = savedInstanceState.getString(STATE_PENDING_OPERATION);
        operand1 = savedInstanceState.getDouble(STATE_OPERAND1);
        binding.operation.setText(pendingOperation);
    }

    private void setNumericOnClickListener() {
        Log.d(TAG, "setNumericOnClickListener() called");
        binding.button0.setOnClickListener(getNumericButtonClickListener());
        binding.button1.setOnClickListener(getNumericButtonClickListener());
        binding.button2.setOnClickListener(getNumericButtonClickListener());
        binding.button3.setOnClickListener(getNumericButtonClickListener());
        binding.button4.setOnClickListener(getNumericButtonClickListener());
        binding.button5.setOnClickListener(getNumericButtonClickListener());
        binding.button6.setOnClickListener(getNumericButtonClickListener());
        binding.button7.setOnClickListener(getNumericButtonClickListener());
        binding.button8.setOnClickListener(getNumericButtonClickListener());
        binding.button9.setOnClickListener(getNumericButtonClickListener());
        binding.buttonDot.setOnClickListener(getNumericButtonClickListener());
    }

    private void setOperationOnClickListener() {
        Log.d(TAG, "setOperationOnClickListener() called");
        binding.buttonEquals.setOnClickListener(getOpListener());
        binding.buttonDivide.setOnClickListener(getOpListener());
        binding.buttonMultiply.setOnClickListener(getOpListener());
        binding.buttonMinus.setOnClickListener(getOpListener());
        binding.buttonPlus.setOnClickListener(getOpListener());
        binding.buttonPercent.setOnClickListener(getOpListener());
    }

    private void setFunctionOnClickListener() {
        Log.d(TAG, "setFunctionOnClickListener() called");
        binding.buttonNeg.setOnClickListener(v -> {
            String value = binding.newNumber.getText().toString();
            if (value.length() == 0) binding.newNumber.setText("-");
            else try {
                double doubleValue = Double.parseDouble(value);
                doubleValue *= -1;
                binding.newNumber.setText(String.valueOf(doubleValue));
            } catch (NumberFormatException e) {
                binding.newNumber.setText("");
            }
        });

        binding.buttonAc.setOnClickListener(v -> {
            binding.newNumber.setText("");
            binding.result.setText("");
            binding.operation.setText("");
            operand1 = null;
            pendingOperation = "";
        });

        binding.buttonDel.setOnClickListener(v -> {
            String value = binding.newNumber.getText().toString();
            try {
                binding.newNumber.setText(value.substring(0, value.length() - 1));
            } catch (StringIndexOutOfBoundsException e) {
                binding.newNumber.setText("");
            }
        });
    }

    private View.OnClickListener getNumericButtonClickListener() {
        Log.d(TAG, "getNumericButtonClickListener() called");
        return v -> binding.newNumber.append(((Button) v).getText().toString());
    }

    private View.OnClickListener getOpListener() {
        Log.d(TAG, "getOpListener() called");
        return v -> {
            String op = ((Button) v).getText().toString();
            String value = binding.newNumber.getText().toString();
            try {
                Double doubleValue = Double.valueOf(value);
                performOperation(doubleValue, op);
            } catch (NumberFormatException e) {
                binding.newNumber.setText("");
            }
            pendingOperation = op;
            binding.operation.setText(pendingOperation);
        };
    }

    private void performOperation(Double value, String operation) {
        Log.d(TAG, "performOperation() called with: value = [" + value + "], operation = [" + operation + "]");
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
        binding.result.setText(String.valueOf(operand1));
        binding.newNumber.setText("");
    }
}