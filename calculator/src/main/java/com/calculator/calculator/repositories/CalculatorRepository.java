package com.calculator.calculator.repositories;

import com.calculator.calculator.models.Calculation;
import jdk.dynalink.Operation;

import java.util.*;

public class CalculatorRepository {

    private Map<UUID, Calculation> data = new HashMap<>();

    public void create(Calculation calculation) {
        this.data.put(calculation.getId(), calculation);
    }

    public List<Calculation> getAll() {
        return this.data.values().stream().toList();
    }

    public Optional<Calculation> getById(UUID id) {
        Calculation calculation = this.data.get(id);
        if(calculation == null) {
            return Optional.empty();
        }
        return Optional.of(calculation);
    }
}
