package com.person.controllers.book;

import com.person.controllers.book.helpers.BookToDto;
import com.person.controllers.book.responses.ResponseBook;
import com.person.models.Book;
import com.person.services.GetAllBooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/book/v1")
public class GetAllBooksController {

    @Autowired
    private GetAllBooksService getAllBooksService;

    @GetMapping
    public ResponseEntity<Object> execute(
            @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
            Pageable pageable
    ) {
        List<Book> books = getAllBooksService.execute(page, pageSize, pageable);
        List<ResponseBook> responseBooks = BookToDto.toResponseListBook(books);
        return ResponseEntity.status(HttpStatus.OK).body(responseBooks);
    }
}
