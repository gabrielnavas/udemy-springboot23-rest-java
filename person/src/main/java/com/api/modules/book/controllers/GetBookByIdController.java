package com.api.modules.book.controllers;

import com.api.exceptions.ObjectNotFoundException;
import com.api.modules.book.controllers.helpers.BookToDto;
import com.api.modules.book.controllers.responses.ResponseBook;
import com.api.modules.book.models.Book;
import com.api.modules.book.services.GetBookByIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/book/v1")
public class GetBookByIdController {

    @Autowired
    private GetBookByIdService getBookByIdService;

    @GetMapping("{bookId}")
    public ResponseEntity<Object> execute(
            @PathVariable("bookId") UUID bookId
    ) {
        Optional<Book> optionalBook =  getBookByIdService.execute(bookId);
        if(optionalBook.isEmpty()) {
            throw new ObjectNotFoundException("book not found");
        }
        ResponseBook bookResponse = BookToDto.toResponseBook(optionalBook.get());
        return ResponseEntity.status(HttpStatus.OK).body(bookResponse);
    }

}
