package com.person.controllers.book;

import com.person.dtos.CreateBookDto;
import com.person.services.UpdatePartialsBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/book/v1")
public class UpdateBookController {
    @Autowired
    private UpdatePartialsBookService updatePartialsBookService;

    @PatchMapping("/{bookId}")
    public ResponseEntity<Object> execute(
            @PathVariable("bookId") UUID bookId,
            @RequestBody CreateBookDto dto
    ) {
        updatePartialsBookService.execute(bookId, dto);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
