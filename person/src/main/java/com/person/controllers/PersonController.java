package com.person.controllers;

import com.person.controllers.dtos.RequestCreateUpdatePartialsPersonDto;
import com.person.controllers.dtos.ResponsePersonDto;
import com.person.exceptions.PasswordAndPasswordConfirmationException;
import com.person.models.Person;
import com.person.services.CreateUpdatePartialsPerson;
import com.person.services.DeletePerson;
import com.person.services.GetAllPersons;
import com.person.services.GetPersonById;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
            @RequestBody @Valid RequestCreateUpdatePartialsPersonDto bodyDto
    ) {
        if (!bodyDto.password().equals(bodyDto.passwordConfirmation())) {
            throw new PasswordAndPasswordConfirmationException();
        }

        Person person = new Person();
        BeanUtils.copyProperties(bodyDto, person);

        person = createUpdatePartialsPerson.create(person);
        ResponsePersonDto responsePersonDto = toResponseBody(person);
        return ResponseEntity.status(HttpStatus.CREATED).body(responsePersonDto);
    }

    @GetMapping(value = "/{personId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getByIdPerson(
            @PathVariable("personId") UUID personId
    ) {
        Person person = getPersonById.execute(personId);
        ResponsePersonDto responsePersonDto = toResponseBody(person);
        return ResponseEntity.status(HttpStatus.OK).body(responsePersonDto);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getAllPersons(
            @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
            Pageable pageable
    ) {
        Page<Person> personsPages = getAllPersons.execute(pageable);
        Collection<ResponsePersonDto> responseBody = toResponseListBody(personsPages.stream().toList());

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
            @RequestBody @Valid RequestCreateUpdatePartialsPersonDto bodyDto
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

    private Collection<ResponsePersonDto> toResponseListBody(Collection<Person> persons) {
        List<ResponsePersonDto> responseBody = new ArrayList<>();
        for (Person person : persons) {
            responseBody.add(toResponseBody(person));
        }
        return responseBody;
    }

    private ResponsePersonDto toResponseBody(Person person) {
        return new ResponsePersonDto(
                person.getId().toString(),
                person.getFirstname(),
                person.getLastname(),
                person.getBirthday(),
                person.getUsername(),
                person.getEmail(),
                person.getCreatedAt(),
                person.getUpdateAt()
        );
    }
}
