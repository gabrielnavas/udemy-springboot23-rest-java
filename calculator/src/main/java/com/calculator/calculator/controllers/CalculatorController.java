package com.calculator.calculator.controllers;

import com.calculator.calculator.controllers.dtos.RequestCreateCalculate;
import com.calculator.calculator.exceptions.ObjectNotFoundException;
import com.calculator.calculator.models.Calculation;
import com.calculator.calculator.repositories.CalculatorRepository;
import com.calculator.calculator.services.MathService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/calc")
public class CalculatorController {

    @Autowired
    private CalculatorRepository calculatorRepository;

    @Autowired
    private MathService mathService;

    @PostMapping
    public ResponseEntity<Object> createCalculate(
            @RequestBody @Valid RequestCreateCalculate body
    ) {
        Double result = mathService.execute(body.numberOne(), body.operation().charAt(0), body.numberTwo());

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

    @GetMapping
    public ResponseEntity<Object> getAll() {
        List<Calculation> calculations = calculatorRepository.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(calculations.toArray());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(
            @PathVariable UUID id
    ) {
        Optional<Calculation> calculation = calculatorRepository.getById(id);
        if (calculation.isEmpty()) {
            throw new ObjectNotFoundException("calculation not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(calculation.get());
    }

}
