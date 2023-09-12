package com.person.controllers.person;

import com.person.services.DeletePerson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(value = "/api/person/v1")
public class DeletePersonByIdController {
    @Autowired
    private DeletePerson deletePerson;

    @DeleteMapping(value = "/{personId}")
    public ResponseEntity<Object> execute(
            @PathVariable("personId") UUID personId
    ) {
        deletePerson.execute(personId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
