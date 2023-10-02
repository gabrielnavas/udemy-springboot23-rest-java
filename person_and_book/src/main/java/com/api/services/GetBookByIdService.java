package com.api.services;

import com.api.models.Book;
import com.api.repositories.BookRepositoryJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class GetBookByIdService {

    @Autowired
    private BookRepositoryJpa bookRepository;

    public Optional<Book> execute(UUID id) {
        return bookRepository.findById(id);
    }
}
