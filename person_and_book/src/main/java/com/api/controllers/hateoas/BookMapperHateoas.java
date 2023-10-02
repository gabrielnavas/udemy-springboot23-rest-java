package com.api.controllers.hateoas;

import com.api.controllers.*;
import com.api.controllers.responses.ResponseBook;
import com.api.dtos.CreateUpdatePartialsPersonDto;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Collection;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class BookMapperHateoas {
    static public void set(ResponseBook dto, Pageable pageable, BookHateoasWithRel withRel) {
        dto.add(
                linkTo(methodOn(GetAllPersonsController.class).execute(pageable)).withRel(
                        withRel.equals(BookHateoasWithRel.GET_ALL_BOOKS)
                                ? BookHateoasWithRel.SELF.getDescription()
                                : BookHateoasWithRel.GET_ALL_BOOKS.getDescription()
                )
        );
        set(dto, withRel);
    }

    static public void set(ResponseBook dto, BookHateoasWithRel withRel) {
        Pageable pageRequest = PageRequest.of(0, 10, Sort.by("id"));
        setWithPageable(dto, pageRequest, withRel);
        setWithoutPageable(dto, dto.getKey(), withRel);
    }


    static public void setList(Collection<ResponseBook> responsePersonList, Pageable pageable, BookHateoasWithRel withRel) {
        for (var dto : responsePersonList) {
            setWithPageable(dto, pageable, withRel);
            setWithoutPageable(dto, dto.getKey(), withRel);
        }
    }

    static private void setWithPageable(ResponseBook dto, Pageable pageable, BookHateoasWithRel withRel) {
        dto.add(
                linkTo(methodOn(GetAllPersonsController.class).execute(pageable)).withRel(
                        withRel.equals(BookHateoasWithRel.GET_ALL_BOOKS)
                                ? BookHateoasWithRel.SELF.getDescription()
                                : BookHateoasWithRel.GET_ALL_BOOKS.getDescription()
                )
        );
    }

    static private void setWithoutPageable(ResponseBook dto, UUID personId, BookHateoasWithRel withRel) {
        dto.add(
                linkTo(methodOn(CreatePersonController.class).execute(new CreateUpdatePartialsPersonDto())).withRel(
                        withRel.equals(BookHateoasWithRel.CREATE_BOOK)
                                ? BookHateoasWithRel.SELF.getDescription()
                                : BookHateoasWithRel.CREATE_BOOK.getDescription()
                )
        );
        dto.add(
                linkTo(methodOn(UpdatePartialsPersonController.class).execute(personId, new CreateUpdatePartialsPersonDto())).withRel(
                        withRel.equals(BookHateoasWithRel.UPDATE_PARTIALS_BOOK)
                                ? BookHateoasWithRel.SELF.getDescription()
                                : BookHateoasWithRel.UPDATE_PARTIALS_BOOK.getDescription()
                )
        );
        dto.add(
                linkTo(methodOn(GetPersonByIdController.class).execute(personId)).withRel(
                        withRel.equals(BookHateoasWithRel.GET_BOOK_BY_ID)
                                ? BookHateoasWithRel.SELF.getDescription()
                                : BookHateoasWithRel.GET_BOOK_BY_ID.getDescription()
                )
        );
        dto.add(
                linkTo(methodOn(DeletePersonByIdController.class).execute(personId)).withRel(
                        withRel.equals(BookHateoasWithRel.DELETE_BOOK_BY_ID)
                                ? BookHateoasWithRel.SELF.getDescription()
                                : BookHateoasWithRel.DELETE_BOOK_BY_ID.getDescription()
                )
        );
    }
}
