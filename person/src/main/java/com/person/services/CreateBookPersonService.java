package com.person.services;

import com.person.dtos.CreateBookDto;
import com.person.models.Book;
import com.person.repositories.BookRepositoryJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CreateBookPersonService {

    @Autowired
    private BookRepositoryJpa bookRepository;

    public Book execute(CreateBookDto dto) {
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
