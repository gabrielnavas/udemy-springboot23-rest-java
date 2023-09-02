package com.calculator.calculator.controllers;

import com.calculator.calculator.controllers.dtos.RequestCreateCalculate;
import com.calculator.calculator.exceptions.ObjectNotFoundException;
import com.calculator.calculator.models.Calculation;
import com.calculator.calculator.exceptions.DivisionZeroException;
import com.calculator.calculator.exceptions.OperationNotSupportedException;
import com.calculator.calculator.repositories.CalculatorRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class CalculatorController {

    private final CalculatorRepository calculatorRepository = new CalculatorRepository();

    @PostMapping("/calc")
    public ResponseEntity<Object> createCalculate(
            @RequestBody RequestCreateCalculate body
    ) {

        Double result = 0.0;

        switch (body.operation()) {
            case "+":
                result = body.numberOne() + body.numberTwo();
                break;
            case "-":
                result = body.numberOne() - body.numberTwo();
                break;
            case "*":
                result = body.numberOne() * body.numberTwo();
                break;
            case "/":
                if(body.numberTwo() == Double.parseDouble("0")) {
                    throw new DivisionZeroException();
                }
                result = body.numberOne() / body.numberTwo();
                break;
            case "%":
                if(body.numberTwo() == Double.parseDouble("0")) {
                    throw new DivisionZeroException();
                }
                result = body.numberOne() % body.numberTwo();
                break;
            default:
                throw new OperationNotSupportedException();

        }

        Calculation calculation = new Calculation(
                UUID.randomUUID(),
                body.username(),
                new Date(),
                body.numberOne(),
                body.operation(),
                body.numberTwo(),
                result
        );
        calculatorRepository.create(calculation);

        return ResponseEntity.status(HttpStatus.CREATED).body(calculation);
    }

    @GetMapping("/calc")
    public ResponseEntity<Object> getAll() {
        List<Calculation> calculations = calculatorRepository.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(calculations.toArray());
    }

    @GetMapping("/calc/{id}")
    public ResponseEntity<Object> getById(
            @PathVariable UUID id
    ) {
        Optional<Calculation> calculation = calculatorRepository.getById(id);
        if(calculation.isEmpty()) {
            throw new ObjectNotFoundException("calculation not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(calculation.get());
    }

}
