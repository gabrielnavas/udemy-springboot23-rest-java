package com.person.controllers.person;

import com.person.controllers.person.hateoas.PersonHateoasWithRel;
import com.person.controllers.person.hateoas.PersonMapperHateoas;
import com.person.controllers.person.helpers.PersonToDto;
import com.person.dtos.ResponsePersonDto;
import com.person.models.Person;
import com.person.services.GetPersonById;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(value = "/api/person/v1")
public class GetPersonByIdController {
    @Autowired
    private GetPersonById getPersonById;

    @GetMapping(
            value = "/{personId}",
            produces = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE
            }
    )
    public ResponseEntity<Object> execute(
            @PathVariable("personId") UUID personId
    ) {
        Person person = getPersonById.execute(personId);
        ResponsePersonDto responsePersonDto = PersonToDto.toResponseBody(person);

        PersonMapperHateoas.set(responsePersonDto, personId, 0, 10, null, PersonHateoasWithRel.GET_PERSON_BY_ID);

        return ResponseEntity.status(HttpStatus.OK).body(responsePersonDto);
    }
}
