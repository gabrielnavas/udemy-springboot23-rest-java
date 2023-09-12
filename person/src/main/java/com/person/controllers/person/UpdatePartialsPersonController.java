package com.person.controllers.person;

import com.person.dtos.RequestCreateUpdatePartialsPersonDto;
import com.person.exceptions.PasswordAndPasswordConfirmationException;
import com.person.models.Person;
import com.person.services.CreateUpdatePartialsPerson;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(value = "/api/person/v1")
public class UpdatePartialsPersonController {

    @Autowired
    private CreateUpdatePartialsPerson createUpdatePartialsPerson;

    @PatchMapping(
            value = "/{personId}",
            consumes = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE,
            },
            produces = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE,
            }
    )
    public ResponseEntity<Object> execute(
            @PathVariable("personId") UUID personId,
            @RequestBody @Valid RequestCreateUpdatePartialsPersonDto bodyDto
    ) {
        if (!bodyDto.getPassword().equals(bodyDto.getPasswordConfirmation())) {
            throw new PasswordAndPasswordConfirmationException();
        }

        Person person = new Person();
        person.setId(personId);
        BeanUtils.copyProperties(bodyDto, person);
        createUpdatePartialsPerson.updatePartialsPerson(person);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
