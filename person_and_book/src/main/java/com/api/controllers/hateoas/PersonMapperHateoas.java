package com.api.controllers.hateoas;

import com.api.controllers.*;
import com.api.controllers.responses.ResponsePerson;
import com.api.dtos.CreateUpdatePartialsPersonDto;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Collection;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class PersonMapperHateoas {

    static public void set(ResponsePerson dto, Pageable pageable, PersonHateoasWithRel withRel) {
        dto.add(
                linkTo(methodOn(GetAllPersonsController.class).execute(pageable)).withRel(
                        withRel.equals(PersonHateoasWithRel.GET_ALL_PERSONS)
                                ? PersonHateoasWithRel.SELF.getDescription()
                                : PersonHateoasWithRel.GET_ALL_PERSONS.getDescription()
                )
        );
        set(dto, UUID.fromString(dto.getKey()), withRel);
    }

    static public void set(ResponsePerson dto, UUID personId, PersonHateoasWithRel withRel) {
        Pageable pageRequest = PageRequest.of(0, 10, Sort.by("id"));
        setWithPageable(dto, pageRequest, withRel);
        setWithoutPageable(dto, UUID.fromString(dto.getKey()), withRel);
    }


    static public void setList(Collection<ResponsePerson> responsePersonList, Pageable pageable, PersonHateoasWithRel withRel) {
        for (var dto : responsePersonList) {
            setWithPageable(dto, pageable, withRel);
            setWithoutPageable(dto, UUID.fromString(dto.getKey()), withRel);
        }
    }

    static private void setWithPageable(ResponsePerson dto, Pageable pageable, PersonHateoasWithRel withRel) {
        dto.add(
                linkTo(methodOn(GetAllPersonsController.class).execute(pageable)).withRel(
                        withRel.equals(PersonHateoasWithRel.GET_ALL_PERSONS)
                                ? PersonHateoasWithRel.SELF.getDescription()
                                : PersonHateoasWithRel.GET_ALL_PERSONS.getDescription()
                )
        );
    }

    static private void setWithoutPageable(ResponsePerson dto, UUID personId, PersonHateoasWithRel withRel) {
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
}
