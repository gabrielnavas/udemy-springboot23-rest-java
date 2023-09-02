package com.person.services;

import com.person.controllers.dtos.CreatePersonDto;
import com.person.exceptions.ObjectAlreadyExistsWithException;
import com.person.exceptions.PasswordAndPasswordConfirmationException;
import com.person.models.Person;
import com.person.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Logger;

@Service
public class CreatePerson {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PasswordEncoderBCrypt bCryptPasswordEncoder;

    private final Logger logger = Logger.getLogger(CreatePerson.class.getName());

    public Person execute(CreatePersonDto data) {
        logger.info(String.format("%s - %s", new Date().toString(), "create a person"));

        if (!data.password().equals(data.passwordConfirmation())) {
            throw new PasswordAndPasswordConfirmationException();
        }

        Optional<Person> personByEmailFound = personRepository.getByEmail(data.email());
        if (personByEmailFound.isPresent()) {
            var exception = new ObjectAlreadyExistsWithException("person", "email");
            logger.info(String.format("%s - %s", new Date().toString(), exception.getMessage()));
            throw exception;
        }

        Optional<Person> personByUsernameFound = personRepository.getByUsername(data.username());
        if (personByUsernameFound.isPresent()) {
            var exception = new ObjectAlreadyExistsWithException("person", "username");
            logger.info(String.format("%s - %s", new Date().toString(), exception.getMessage()));
            throw exception;
        }

        String passwordHash = bCryptPasswordEncoder.encode(data.password());

        Person person = new Person(
                UUID.randomUUID(),
                data.firstname(),
                data.lastname(),
                data.username(),
                data.email(),
                passwordHash
        );

        personRepository.add(person);
        return person;
    }
}
