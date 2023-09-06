package com.person.services;

import com.person.exceptions.ObjectAlreadyExistsWithException;
import com.person.models.Person;
import com.person.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Logger;

@Service
public class CreateUpdatePartialsPerson {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PasswordEncoderBCrypt bCryptPasswordEncoder;


    private final Logger logger = Logger.getLogger(CreateUpdatePartialsPerson.class.getName());


    public Person create(Person person) {
        logger.info(String.format("%s - %s", new Date().toString(), "create a person"));

        checkDuplicatedPerson(person);

        String passwordHash = bCryptPasswordEncoder.encode(person.getPassword());
        person.setPassword(passwordHash);

        personRepository.save(person);
        return person;
    }


    public void updatePartialsPerson(UUID id, Person person) {
        logger.info(String.format("%s - %s", new Date().toString(), "update a person"));
        checkDuplicatedPerson(person);

        String passwordHash = bCryptPasswordEncoder.encode(person.getPassword());
        person.setPassword(passwordHash);
        personRepository.updatePartials(id, person);
    }

    private void checkDuplicatedPerson(Person person) {
        Optional<Person> personByEmailFound = personRepository.getByEmail(person.getEmail());
        if (personByEmailFound.isPresent()) {
            var exception = new ObjectAlreadyExistsWithException("person", "email");
            logger.info(String.format("%s - %s", new Date().toString(), exception.getMessage()));
            throw exception;
        }

        Optional<Person> personByUsernameFound = personRepository.getByUsername(person.getUsername());
        if (personByUsernameFound.isPresent()) {
            var exception = new ObjectAlreadyExistsWithException("person", "username");
            logger.info(String.format("%s - %s", new Date().toString(), exception.getMessage()));
            throw exception;
        }
    }

    private void verifyPasswords(Person person) {

    }
}