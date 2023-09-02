package com.person.controllers;

import com.person.controllers.dtos.RequestCreatePersonBodyDto;
import com.person.controllers.dtos.ResponseCreatePersonBody;
import com.person.exceptions.PasswordAndPasswordConfirmationException;
import com.person.models.Person;
import com.person.repositories.PersonRepository;
import com.person.services.PasswordEncoderBCrypt;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(value = "/person")
public class PersonController {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PasswordEncoderBCrypt bCryptPasswordEncoder;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> create(
            @RequestBody @Valid RequestCreatePersonBodyDto body
    ) {

        if (!body.password().equals(body.passwordConfirmation())) {
            // TODO: Create handlers exceptions classes
            throw new PasswordAndPasswordConfirmationException();
        }

        String passwordHash = bCryptPasswordEncoder.encode(body.password());

        Person person = new Person(
                UUID.randomUUID(),
                body.firstname(),
                body.lastname(),
                body.username(),
                body.email(),
                passwordHash
        );

        personRepository.add(person);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseCreatePersonBody(
                person.getId().toString(),
                person.getFirstname(),
                person.getLastname(),
                person.getUsername(),
                person.getEmail()
        ));
    }
}
