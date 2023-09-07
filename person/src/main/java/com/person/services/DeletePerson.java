package com.person.services;

import com.person.exceptions.ObjectNotFoundException;
import com.person.models.Person;
import com.person.repositories.PersonRepositoryJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DeletePerson {

    @Autowired
    private PersonRepositoryJpa personRepository;

    public void execute(UUID personId) {
        Person person = personRepository.findById(personId)
                .orElseThrow(() -> new ObjectNotFoundException("person not found"));
        personRepository.delete(person);
    }
}
