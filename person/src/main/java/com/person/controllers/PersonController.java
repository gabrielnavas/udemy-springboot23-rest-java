package com.person.controllers;

import com.person.controllers.dtos.CreatePersonDto;
import com.person.controllers.dtos.ResponsePersonBody;
import com.person.models.Person;
import com.person.services.CreatePerson;
import com.person.services.GetAllPersons;
import com.person.services.GetPersonById;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
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
    private GetAllPersons getAllPersons;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> create(
            @RequestBody @Valid CreatePersonDto bodyDto
    ) {
        Person person = createPerson.execute(bodyDto);
        ResponsePersonBody responsePersonBody = toResponseBody(person);
        return ResponseEntity.status(HttpStatus.CREATED).body(responsePersonBody);
    }

    @GetMapping(value = "/{personId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getById(
            @PathVariable("personId") UUID personId
    ) {
        Person person = getPersonById.execute(personId);
        ResponsePersonBody responsePersonBody = toResponseBody(person);
        return ResponseEntity.status(HttpStatus.CREATED).body(responsePersonBody);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getAll() {
        Collection<Person> persons = getAllPersons.execute();
        Collection<ResponsePersonBody> responseBody = toResponseListBody(persons);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseBody);
    }

    private Collection<ResponsePersonBody> toResponseListBody(Collection<Person> persons) {
        List<ResponsePersonBody> responseBody = new ArrayList<>();
        for (Person person : getAllPersons.execute()) {
            responseBody.add(toResponseBody(person));
        }
        return responseBody;
    }

    private ResponsePersonBody toResponseBody(Person person) {
        return new ResponsePersonBody(
                person.getId().toString(),
                person.getFirstname(),
                person.getLastname(),
                person.getUsername(),
                person.getEmail()
        );
    }
}
