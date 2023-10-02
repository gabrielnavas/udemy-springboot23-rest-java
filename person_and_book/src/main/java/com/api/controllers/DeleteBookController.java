package com.api.controllers;

import com.api.exceptions.ObjectNotFoundException;
import com.api.services.DeleteBookService;
import com.api.services.GetBookByIdService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/book/v1")
@Tag(name = "Book", description = "Endpoints for Managing Books")
public class DeleteBookController {

    @Autowired
    private DeleteBookService deleteBookService;

    @Autowired
    private GetBookByIdService getBookByIdService;

    @DeleteMapping("/{bookId}")
    @Operation(summary = "delete a book", description = "delete a people",
            tags = {"People"},
            responses = {
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content),
            }
    )
    public ResponseEntity<Object> execute(@PathVariable("bookId") UUID bookId) {
        getBookByIdService
                .execute(bookId)
                .ifPresentOrElse(
                        book -> deleteBookService.execute(book),
                        () -> {
                            throw new ObjectNotFoundException("book not found");
                        }
                );
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
