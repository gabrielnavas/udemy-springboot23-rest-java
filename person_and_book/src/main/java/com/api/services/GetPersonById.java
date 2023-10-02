package com.api.services;

import com.api.exceptions.ObjectNotFoundException;
import com.api.models.Person;
import com.api.repositories.PersonRepositoryJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
public class GetPersonById {

    @Autowired
    private PersonRepositoryJpa personRepository;

    public Person execute(UUID personId) {
        return personRepository.findById(personId)
                .orElseThrow(() -> new ObjectNotFoundException("person not found"));
    }
}
