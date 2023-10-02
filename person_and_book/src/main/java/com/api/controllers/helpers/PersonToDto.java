package com.api.controllers.helpers;

import com.api.controllers.responses.ResponsePerson;
import com.api.models.Person;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PersonToDto {
    static public Collection<ResponsePerson> toResponseListBody(Collection<Person> persons) {
        List<ResponsePerson> responseBody = new ArrayList<>();
        for (Person person : persons) {
            responseBody.add(toResponseBody(person));
        }
        return responseBody;
    }

    static public ResponsePerson toResponseBody(Person person) {
        return new ResponsePerson(
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
