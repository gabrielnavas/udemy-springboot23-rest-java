package com.api.services;

import com.api.models.Person;
import com.api.repositories.PersonRepositoryJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class GetAllPersons {

    @Autowired
    private PersonRepositoryJpa personRepository;

    public Page<Person> execute(Pageable pageable) {
        return personRepository.findAll(pageable);
    }
}
