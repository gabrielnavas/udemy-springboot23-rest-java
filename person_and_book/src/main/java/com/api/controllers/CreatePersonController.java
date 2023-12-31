package com.api.controllers;

import com.api.controllers.hateoas.PersonHateoasWithRel;
import com.api.controllers.hateoas.PersonMapperHateoas;
import com.api.controllers.helpers.PersonToDto;
import com.api.controllers.responses.ResponsePerson;
import com.api.dtos.CreateUpdatePartialsPersonDto;
import com.api.exceptions.PasswordAndPasswordConfirmationException;
import com.api.models.Person;
import com.api.services.CreateUpdatePartialsPerson;
import com.api.services.DeletePerson;
import com.api.services.GetAllPersons;
import com.api.services.GetPersonById;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/person/v1")
@Tag(name = "People", description = "Endpoints for Managing People")
public class CreatePersonController {

    @Autowired
    private CreateUpdatePartialsPerson createUpdatePartialsPerson;

    @Autowired
    private GetPersonById getPersonById;

    @Autowired
    private GetAllPersons getAllPersons;

    @Autowired
    private DeletePerson deletePerson;


    @PostMapping(
            consumes = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE
            },
            produces = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE
            }
    )
    @Operation(summary = "create a people", description = "create a people by passing in JSON or XML representation of the person",
            tags = {"People"},
            responses = {
                    @ApiResponse(description = "Created", responseCode = "201", content = @Content(
                            schema = @Schema(implementation = ResponsePerson.class)
                    )),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content),
            }
    )
    public ResponseEntity<Object> execute(
            @RequestBody @Valid CreateUpdatePartialsPersonDto bodyDto
    ) {
        if (!bodyDto.getPassword().equals(bodyDto.getPasswordConfirmation())) {
            throw new PasswordAndPasswordConfirmationException();
        }

        Person person = new Person();
        BeanUtils.copyProperties(bodyDto, person);

        person = createUpdatePartialsPerson.create(person);
        ResponsePerson responsePerson = PersonToDto.toResponseBody(person);

        PersonMapperHateoas.set(responsePerson, person.getId(), PersonHateoasWithRel.CREATE_PERSON);

        return ResponseEntity.status(HttpStatus.CREATED).body(responsePerson);
    }
}
