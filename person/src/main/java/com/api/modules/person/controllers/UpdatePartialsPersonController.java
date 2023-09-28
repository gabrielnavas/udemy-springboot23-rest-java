package com.api.modules.person.controllers;

import com.api.modules.person.dtos.CreateUpdatePartialsPersonDto;
import com.api.modules.person.exceptions.PasswordAndPasswordConfirmationException;
import com.api.modules.person.models.Person;
import com.api.modules.person.services.CreateUpdatePartialsPerson;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "People", description = "Endpoints for Managing People")
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
    @Operation(summary = "update partials people", description = "update partials people",
            tags = {"People"},
            responses = {
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content),
            }
    )
    public ResponseEntity<Object> execute(
            @PathVariable("personId") UUID personId,
            @RequestBody @Valid CreateUpdatePartialsPersonDto bodyDto
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
