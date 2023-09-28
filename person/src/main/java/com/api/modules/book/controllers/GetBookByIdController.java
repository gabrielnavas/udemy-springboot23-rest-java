package com.api.modules.book.controllers;

import com.api.exceptions.ObjectNotFoundException;
import com.api.modules.book.controllers.hateoas.BookHateoasWithRel;
import com.api.modules.book.controllers.hateoas.BookMapperHateoas;
import com.api.modules.book.controllers.helpers.BookToDto;
import com.api.modules.book.controllers.responses.ResponseBook;
import com.api.modules.book.models.Book;
import com.api.modules.book.services.GetBookByIdService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/book/v1")
@Tag(name = "Book", description = "Endpoints for Managing Books")
public class GetBookByIdController {

    @Autowired
    private GetBookByIdService getBookByIdService;

    @GetMapping(
            value = "{bookId}",
            produces = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE
            })
    @Operation(summary = "find a book", description = "find a people",
            tags = {"People"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ResponseBook.class)
                            )
                    }),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content),
            }
    )
    public ResponseEntity<Object> execute(
            @PathVariable("bookId") UUID bookId
    ) {
        Optional<Book> optionalBook = getBookByIdService.execute(bookId);
        if (optionalBook.isEmpty()) {
            throw new ObjectNotFoundException("book not found");
        }
        ResponseBook bookResponse = BookToDto.toResponseBook(optionalBook.get());
        BookMapperHateoas.set(bookResponse, BookHateoasWithRel.GET_BOOK_BY_ID);
        return ResponseEntity.status(HttpStatus.OK).body(bookResponse);
    }

}
