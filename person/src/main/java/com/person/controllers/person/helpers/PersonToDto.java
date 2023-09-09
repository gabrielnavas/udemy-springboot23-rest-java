package com.person.controllers.person.helpers;

import com.person.dtos.ResponsePersonDto;
import com.person.models.Person;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PersonToDto {
    static public Collection<ResponsePersonDto> toResponseListBody(Collection<Person> persons) {
        List<ResponsePersonDto> responseBody = new ArrayList<>();
        for (Person person : persons) {
            responseBody.add(toResponseBody(person));
        }
        return responseBody;
    }

    static public ResponsePersonDto toResponseBody(Person person) {
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
