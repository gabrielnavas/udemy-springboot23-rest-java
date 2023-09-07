package com.person.controllers;

import com.person.controllers.dtos.CreateUpdatePartialsPersonDto;
import com.person.controllers.dtos.ResponsePersonBody;
import com.person.exceptions.PasswordAndPasswordConfirmationException;
import com.person.models.Person;
import com.person.services.CreateUpdatePartialsPerson;
import com.person.services.DeletePerson;
import com.person.services.GetAllPersons;
import com.person.services.GetPersonById;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
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
    private CreateUpdatePartialsPerson createUpdatePartialsPerson;

    @Autowired
    private GetPersonById getPersonById;

    @Autowired
    private GetAllPersons getAllPersons;

    @Autowired
    private DeletePerson deletePerson;


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createPerson(
            @RequestBody @Valid CreateUpdatePartialsPersonDto bodyDto
    ) {
        if (!bodyDto.password().equals(bodyDto.passwordConfirmation())) {
            throw new PasswordAndPasswordConfirmationException();
        }

        Person person = new Person();
        BeanUtils.copyProperties(bodyDto, person);

        person = createUpdatePartialsPerson.create(person);
        ResponsePersonBody responsePersonBody = toResponseBody(person);
        return ResponseEntity.status(HttpStatus.CREATED).body(responsePersonBody);
    }

    @GetMapping(value = "/{personId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getByIdPerson(
            @PathVariable("personId") UUID personId
    ) {
        Person person = getPersonById.execute(personId);
        ResponsePersonBody responsePersonBody = toResponseBody(person);
        return ResponseEntity.status(HttpStatus.OK).body(responsePersonBody);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getAllPersons() {
        Collection<Person> persons = getAllPersons.execute();
        Collection<ResponsePersonBody> responseBody = toResponseListBody(persons);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    @DeleteMapping(value = "/{personId}")
    public ResponseEntity<Object> deletePerson(
            @PathVariable("personId") UUID personId
    ) {
        deletePerson.execute(personId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PatchMapping(value = "/{personId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updatePartialsPerson(
            @PathVariable("personId") UUID personId,
            @RequestBody @Valid CreateUpdatePartialsPersonDto bodyDto
    ) {
        if (!bodyDto.password().equals(bodyDto.passwordConfirmation())) {
            throw new PasswordAndPasswordConfirmationException();
        }

        Person person = new Person();
        person.setId(personId);
        BeanUtils.copyProperties(bodyDto, person);
        createUpdatePartialsPerson.updatePartialsPerson(person);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
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
