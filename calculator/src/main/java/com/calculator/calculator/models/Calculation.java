package com.calculator.calculator.models;

import java.util.Date;
import java.util.UUID;

public class Calculation {
    private UUID id;

    private final String username;
    private final Date timestamps;
    private final Double numberOne;
    private final String operation;
    private final Double numberTwo;

    private final Double result;

    public Calculation(UUID id, String username, Date timestamps, Double numberOne, String operation, Double numberTwo, Double result) {
        this.id = id;
        this.username = username;
        this.timestamps = timestamps;
        this.numberOne = numberOne;
        this.operation = operation;
        this.numberTwo = numberTwo;
        this.result = result;
    }

    public UUID getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public Date getTimestamps() {
        return timestamps;
    }

    public Double getNumberOne() {
        return numberOne;
    }

    public String getOperation() {
        return operation;
    }

    public Double getNumberTwo() {
        return numberTwo;
    }

    public Double getResult() {
        return result;
    }
}

