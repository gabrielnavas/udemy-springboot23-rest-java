package com.person.services;

import com.person.exceptions.ObjectNotFoundException;
import com.person.models.Person;
import com.person.repositories.PersonRepositoryMemory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;


@Service
public class GetPersonById {

    @Autowired
    private PersonRepositoryMemory personRepository;

    public Person execute(UUID personId) {
        Optional<Person> personFind = personRepository.getById(personId);
        if (personFind.isEmpty()) {
            throw new ObjectNotFoundException("person not found");
        }

        return personFind.get();
    }
}
