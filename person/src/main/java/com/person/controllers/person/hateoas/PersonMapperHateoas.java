package com.person.controllers.person.hateoas;

import com.person.controllers.person.*;
import com.person.controllers.person.responses.ResponsePerson;
import com.person.dtos.CreateUpdatePartialsPersonDto;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class PersonMapperHateoas {

    static public void set(ResponsePerson dto, UUID personId, int page, int pageSize, Pageable pageable, PersonHateoasWithRel withRel) {
        dto.add(
                linkTo(methodOn(CreatePersonController.class).execute(new CreateUpdatePartialsPersonDto())).withRel(
                        withRel.equals(PersonHateoasWithRel.CREATE_PERSON)
                                ? PersonHateoasWithRel.SELF.getDescription()
                                : PersonHateoasWithRel.CREATE_PERSON.getDescription()
                )
        );
        dto.add(
                linkTo(methodOn(UpdatePartialsPersonController.class).execute(personId, new CreateUpdatePartialsPersonDto())).withRel(
                        withRel.equals(PersonHateoasWithRel.UPDATE_PARTIALS_PERSON)
                                ? PersonHateoasWithRel.SELF.getDescription()
                                : PersonHateoasWithRel.UPDATE_PARTIALS_PERSON.getDescription()
                )
        );
        dto.add(
                linkTo(methodOn(GetAllPersonsController.class).execute(page, pageSize, pageable)).withRel(
                        withRel.equals(PersonHateoasWithRel.GET_ALL_PERSONS)
                                ? PersonHateoasWithRel.SELF.getDescription()
                                : PersonHateoasWithRel.GET_ALL_PERSONS.getDescription()
                )
        );
        dto.add(
                linkTo(methodOn(GetPersonByIdController.class).execute(personId)).withRel(
                        withRel.equals(PersonHateoasWithRel.GET_PERSON_BY_ID)
                                ? PersonHateoasWithRel.SELF.getDescription()
                                : PersonHateoasWithRel.GET_PERSON_BY_ID.getDescription()
                )
        );
        dto.add(
                linkTo(methodOn(DeletePersonByIdController.class).execute(personId)).withRel(
                        withRel.equals(PersonHateoasWithRel.DELETE_PERSON_BY_ID)
                                ? PersonHateoasWithRel.SELF.getDescription()
                                : PersonHateoasWithRel.DELETE_PERSON_BY_ID.getDescription()
                )
        );
    }


    static public void setList(Collection<ResponsePerson> responsePersonList, int page, int pageSize, Pageable pageable, PersonHateoasWithRel withRel) {
        for (var dto : responsePersonList) {
            set(dto, UUID.fromString(dto.getKey()), page, pageSize, pageable, withRel);
        }
    }
}
