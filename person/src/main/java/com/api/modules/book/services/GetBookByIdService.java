package com.api.modules.book.services;

import com.api.modules.book.repositories.BookRepositoryJpa;
import com.api.modules.book.models.Book;
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
