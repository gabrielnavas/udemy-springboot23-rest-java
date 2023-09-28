package com.api.modules.book.controllers;

import com.api.modules.book.controllers.helpers.BookToDto;
import com.api.modules.book.controllers.responses.ResponseBook;
import com.api.modules.person.dtos.CreateBookDto;
import com.api.modules.book.models.Book;
import com.api.modules.book.services.CreateBookPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/book/v1")
public class CreateBookController {

    @Autowired
    private CreateBookPersonService createBookPersonService;

    @PostMapping
    public ResponseEntity<Object> execute(
            @RequestBody CreateBookDto dto
    ) {
        Book book = createBookPersonService.execute(dto);
        ResponseBook responseBookDto = BookToDto.toResponseBook(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseBookDto);
    }
}
