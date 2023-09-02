package com.calculator.calculator.controllers.dtos;


import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public record RequestCreateCalculate(
        @Length(min = 2, max = 25)
        String username,

        @NotNull(message = "number one is empty")
        Double numberOne,

        @Length(min = 1, max = 1)
        @NotNull(message = "operation is empty")
        String operation,

        @NotNull(message = "number two is empty")
        Double numberTwo
) { }
