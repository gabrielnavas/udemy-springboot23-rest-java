package com.person.controllers;

import com.person.controllers.dtos.RequestCreatePersonBodyDto;
import com.person.controllers.dtos.ResponsePersonBody;
import com.person.exceptions.ObjectNotFoundException;
import com.person.exceptions.PasswordAndPasswordConfirmationException;
import com.person.models.Person;
import com.person.repositories.PersonRepository;
import com.person.services.PasswordEncoderBCrypt;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponsePersonBody(
                person.getId().toString(),
                person.getFirstname(),
                person.getLastname(),
                person.getUsername(),
                person.getEmail()
        ));
    }

    @GetMapping(value = "/{personId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getById(
            @PathVariable("personId") String personId
    ) {
        Optional<Person> personFind = personRepository.getById(UUID.fromString(personId));
        if (personFind.isEmpty()) {
            throw new ObjectNotFoundException("person not found");
        }

        Person person = personFind.get();

        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponsePersonBody(
                person.getId().toString(),
                person.getFirstname(),
                person.getLastname(),
                person.getUsername(),
                person.getEmail()
        ));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getAll() {
        List<ResponsePersonBody> responseBody = new ArrayList<>();
        for (Person person : personRepository.getAll()) {
            responseBody.add(new ResponsePersonBody(
                    person.getId().toString(),
                    person.getFirstname(),
                    person.getLastname(),
                    person.getUsername(),
                    person.getEmail()
            ));
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(responseBody);
    }
}
