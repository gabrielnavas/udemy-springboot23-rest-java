package com.calculator.calculator.controllers.dtos;


public record RequestCreateCalculate(
        String username,

        Double numberOne,

        String operation,
        Double numberTwo
) { }
