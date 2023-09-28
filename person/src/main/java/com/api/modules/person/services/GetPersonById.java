package com.api.modules.person.services;

import com.api.exceptions.ObjectNotFoundException;
import com.api.modules.person.repositories.PersonRepositoryJpa;
import com.api.modules.person.models.Person;
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
