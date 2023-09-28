package com.api.modules.book.controllers;

import com.api.modules.book.services.DeleteBookService;
import com.api.modules.book.services.GetBookByIdService;
import com.api.exceptions.ObjectNotFoundException;
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
public class DeleteBookController {

    @Autowired
    private DeleteBookService deleteBookService;

    @Autowired
    private GetBookByIdService getBookByIdService;

    @DeleteMapping("/{bookId}")
    public ResponseEntity<Object> execute(@PathVariable("bookId") UUID bookId) {
        getBookByIdService
                .execute(bookId)
                .ifPresentOrElse(
                        book -> deleteBookService.execute(book),
                        () -> { throw new ObjectNotFoundException("book not found"); }
                );
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
