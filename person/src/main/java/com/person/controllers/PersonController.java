package com.person.controllers;

import com.person.controllers.dtos.CreatePersonDto;
import com.person.controllers.dtos.ResponsePersonBody;
import com.person.models.Person;
import com.person.repositories.PersonRepository;
import com.person.services.CreatePerson;
import com.person.services.GetPersonById;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/person")
public class PersonController {

    @Autowired
    private CreatePerson createPerson;

    @Autowired
    private GetPersonById getPersonById;


    @Autowired
    private PersonRepository personRepository;


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> create(
            @RequestBody @Valid CreatePersonDto bodyDto
    ) {
        Person person = createPerson.execute(bodyDto);

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
            @PathVariable("personId") UUID personId
    ) {
        Person person = getPersonById.execute(personId);

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
