package com.person.services;

import com.person.exceptions.ObjectNotFoundException;
import com.person.models.Person;
import com.person.repositories.PersonRepositoryJpa;
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
