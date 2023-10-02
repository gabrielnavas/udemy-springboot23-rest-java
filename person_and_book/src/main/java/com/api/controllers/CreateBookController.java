package com.api.controllers;

import com.api.controllers.hateoas.BookHateoasWithRel;
import com.api.controllers.hateoas.BookMapperHateoas;
import com.api.controllers.helpers.BookToDto;
import com.api.controllers.responses.ResponseBook;
import com.api.dtos.CreateBookDto;
import com.api.models.Book;
import com.api.services.CreateBookPersonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/book/v1")
@Tag(name = "Book", description = "Endpoints for Managing Books")
public class CreateBookController {

    @Autowired
    private CreateBookPersonService createBookPersonService;

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
    @Operation(summary = "create a book", description = "create a book by passing in JSON or XML representation of the book",
            tags = {"Book"},
            responses = {
                    @ApiResponse(description = "Created", responseCode = "201", content = @Content(
                            schema = @Schema(implementation = ResponseBook.class)
                    )),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content),
            }
    )
    public ResponseEntity<Object> execute(
            @RequestBody CreateBookDto dto
    ) {
        Book book = createBookPersonService.execute(dto);
        ResponseBook responseBookDto = BookToDto.toResponseBook(book);
        BookMapperHateoas.set(responseBookDto, BookHateoasWithRel.CREATE_BOOK);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseBookDto);
    }
}
