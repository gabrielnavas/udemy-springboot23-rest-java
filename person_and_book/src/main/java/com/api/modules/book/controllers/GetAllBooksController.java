package com.api.modules.book.controllers;

import com.api.modules.book.controllers.hateoas.BookHateoasWithRel;
import com.api.modules.book.controllers.hateoas.BookMapperHateoas;
import com.api.modules.book.controllers.helpers.BookToDto;
import com.api.modules.book.controllers.responses.ResponseBook;
import com.api.modules.book.models.Book;
import com.api.modules.book.services.GetAllBooksService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/book/v1")
@Tag(name = "Book", description = "Endpoints for Managing Books")
public class GetAllBooksController {

    @Autowired
    private GetAllBooksService getAllBooksService;

    @GetMapping(
            produces = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE
            }
    )
    @Operation(summary = "finds all books", description = "finds all people",
            tags = {"People"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(schema = @Schema(implementation = ResponseBook.class))
                            )
                    }),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content),
            }
    )
    public ResponseEntity<Object> execute(
            Pageable pageable
    ) {
        List<Book> books = getAllBooksService.execute(pageable).stream().toList();
        List<ResponseBook> responseBooks = BookToDto.toResponseListBook(books);
        BookMapperHateoas.setList(responseBooks, pageable, BookHateoasWithRel.GET_ALL_BOOKS);
        return ResponseEntity.status(HttpStatus.OK).body(responseBooks);
    }
}
