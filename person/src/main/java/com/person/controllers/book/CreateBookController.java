package com.person.controllers.book;

import com.person.controllers.book.helpers.BookToDto;
import com.person.controllers.book.responses.ResponseBook;
import com.person.dtos.CreateBookDto;
import com.person.models.Book;
import com.person.services.CreateBookPersonService;
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
