package com.person.services;

import com.person.exceptions.ObjectNotFoundException;
import com.person.models.Person;
import com.person.repositories.PersonRepositoryMemory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class DeletePerson {

    @Autowired
    private PersonRepositoryMemory personRepository;

    public void execute(UUID id) {
        Optional<Person> optionalPerson = personRepository.getById(id);
        if (optionalPerson.isEmpty()) {
            throw new ObjectNotFoundException("person not found on delete");
        }
        personRepository.delete(optionalPerson.get());
    }
}
