package com.person.controllers.person;

import com.person.controllers.person.hateoas.PersonHateoasWithRel;
import com.person.controllers.person.hateoas.PersonMapperHateoas;
import com.person.controllers.person.helpers.PersonToDto;
import com.person.dtos.ResponsePersonDto;
import com.person.models.Person;
import com.person.services.GetAllPersons;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping(value = "/person/v1")
public class GetAllPersonsController {
    @Autowired
    private GetAllPersons getAllPersons;

    @GetMapping(
            produces = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE
            }
    )
    public ResponseEntity<Object> execute(
            @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
            Pageable pageable
    ) {
        Page<Person> personsPages = getAllPersons.execute(pageable);
        Collection<ResponsePersonDto> responseBody = PersonToDto.toResponseListBody(personsPages.stream().toList());

        PersonMapperHateoas.setList(responseBody, 0, 10, null, PersonHateoasWithRel.GET_ALL_PERSONS);

        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}