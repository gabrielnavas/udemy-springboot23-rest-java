package com.calculator.calculator.services;

import com.calculator.calculator.exceptions.DivisionZeroException;
import com.calculator.calculator.exceptions.OperationNotSupportedException;

import java.util.HashMap;
import java.util.Map;

interface MathInputMatch {
    Double apply(Double numberOne, Double numberTwo);
}

public class MathService {

    private final Map<Character, MathInputMatch> calculateMap = new HashMap<>() {{
        put('+', MathService.this::sum);
        put('-', MathService.this::subtraction);
        put('*', MathService.this::multiply);
        put('/', MathService.this::division);
        put('%', MathService.this::rest);
    }};

    public Double execute(Double numberOne, Character operation, Double numberTwo) {
        MathInputMatch func = calculateMap.get(operation);
        if(func == null) {
            throw new OperationNotSupportedException();
        }

        return func.apply(numberOne, numberTwo);
    }

    private Double sum(Double numberOne, Double numberTwo) {
        return numberOne + numberTwo;
    }

    private Double subtraction(Double numberOne, Double numberTwo) {
        return numberOne - numberTwo;
    }

    private Double multiply(Double numberOne, Double numberTwo) {
        return numberOne * numberTwo;
    }

    private Double division(Double numberOne, Double numberTwo) {
        if(numberTwo == Double.parseDouble("0")) {
            throw new DivisionZeroException();
        }
        return numberOne / numberTwo;
    }

    private Double rest(Double numberOne, Double numberTwo) {
        if(numberTwo == Double.parseDouble("0")) {
            throw new DivisionZeroException();
        }
        return numberOne % numberTwo;
    }
}
