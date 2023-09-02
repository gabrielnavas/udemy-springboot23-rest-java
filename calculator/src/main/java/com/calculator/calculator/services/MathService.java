package com.calculator.calculator.services;

import com.calculator.calculator.exceptions.DivisionZeroException;
import com.calculator.calculator.exceptions.OperationNotSupportedException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

interface MathInputMatch {
    Double apply(Double numberOne, Double numberTwo);
}

@Service
public class MathService {


    private final Logger logger = Logger.getLogger(MathService.class.getName());

    private final Map<Character, MathInputMatch> calculateMap = new HashMap<>() {{
        put('+', MathService.this::sum);
        put('-', MathService.this::subtraction);
        put('*', MathService.this::multiply);
        put('/', MathService.this::division);
        put('%', MathService.this::rest);
    }};

    public Double execute(Double numberOne, Character operation, Double numberTwo) {
        logger.info(String.format("executing calculate with number one %.2f %s %.2f", numberOne, operation, numberTwo));
        MathInputMatch func = calculateMap.get(operation);
        if (func == null) {
            OperationNotSupportedException exception = new OperationNotSupportedException();
            logger.warning(exception.getMessage());
            throw exception;
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
        if (numberTwo == Double.parseDouble("0")) {
            throw new DivisionZeroException();
        }
        return numberOne / numberTwo;
    }

    private Double rest(Double numberOne, Double numberTwo) {
        if (numberTwo == Double.parseDouble("0")) {
            throw new DivisionZeroException();
        }
        return numberOne % numberTwo;
    }
}
