package com.calculator.calculator.exceptions;

public class DivisionZeroException extends RuntimeException {

    public DivisionZeroException() {
        super("Division with zero is impossible.");
    }
}
