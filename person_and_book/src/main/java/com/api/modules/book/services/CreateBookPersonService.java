package com.api.modules.book.services;

import com.api.modules.book.repositories.BookRepositoryJpa;
import com.api.modules.person.dtos.CreateBookDto;
import com.api.exceptions.ObjectAlreadyExistsWithException;
import com.api.modules.book.models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class CreateBookPersonService {

    @Autowired
    private BookRepositoryJpa bookRepository;

    public Book execute(CreateBookDto dto) {
        Optional<Book> bookByTitleFound = bookRepository.findByTitle(dto.getTitle());
        if (bookByTitleFound.isPresent()) {
            throw new ObjectAlreadyExistsWithException("book", "title");
        }

        Book book = new Book(
                UUID.randomUUID(),
                dto.getTitle(),
                dto.getAuthor(),
                dto.getPrice(),
                dto.getLaunchDate()
        );
        return bookRepository.save(book);
    }
}
