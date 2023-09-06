package com.person.services;

import com.person.models.Person;
import com.person.repositories.PersonRepositoryMemory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class GetAllPersons {

    @Autowired
    private PersonRepositoryMemory personRepository;

    public Collection<Person> execute() {
        return personRepository.getAll();
    }
}
