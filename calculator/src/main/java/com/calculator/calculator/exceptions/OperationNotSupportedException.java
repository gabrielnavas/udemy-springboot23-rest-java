package com.calculator.calculator.exceptions;

public class OperationNotSupportedException  extends RuntimeException {

    public OperationNotSupportedException() {
        super("Operation should by + - * / %.");
    }
}
