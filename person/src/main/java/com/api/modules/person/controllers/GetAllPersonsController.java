package com.api.modules.person.controllers;

import com.api.modules.person.controllers.hateoas.PersonHateoasWithRel;
import com.api.modules.person.controllers.hateoas.PersonMapperHateoas;
import com.api.modules.person.controllers.helpers.PersonToDto;
import com.api.modules.person.controllers.responses.ResponsePerson;
import com.api.modules.person.models.Person;
import com.api.modules.person.services.GetAllPersons;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping(value = "/api/person/v1")
@Tag(name = "People", description = "Endpoints for Managing People")
public class GetAllPersonsController {
    @Autowired
    private GetAllPersons getAllPersons;

    @GetMapping(
            produces = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE
            }
    )
    @Operation(summary = "finds all people", description = "finds all people",
            tags = {"People"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(schema = @Schema(implementation = ResponsePerson.class))
                            )
                    }),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content),
            }
    )
    public ResponseEntity<Object> execute(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
            @RequestParam(value = "sort", defaultValue = "lastname") String[] sortsDefault // TODO: como passar parametros escolhendo se é desc ou asc
    ) {
        Sort sort = Sort.by(sortsDefault);
        Pageable pageable = PageRequest.of(page, pageSize, sort);

        Page<Person> personsPages = getAllPersons.execute(pageable);
        Collection<ResponsePerson> responseBody = PersonToDto.toResponseListBody(personsPages.stream().toList());

        PersonMapperHateoas.setList(responseBody, page, pageSize, sortsDefault, PersonHateoasWithRel.GET_ALL_PERSONS);

        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}
