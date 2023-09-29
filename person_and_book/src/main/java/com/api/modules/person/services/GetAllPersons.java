package com.api.modules.person.services;

import com.api.modules.person.repositories.PersonRepositoryJpa;
import com.api.modules.person.models.Person;
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
