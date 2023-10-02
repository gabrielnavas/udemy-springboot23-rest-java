package com.api.services;

import com.api.models.Book;
import com.api.repositories.BookRepositoryJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class GetAllBooksService {

    @Autowired
    private BookRepositoryJpa bookRepository;

    public Page<Book> execute(
            Pageable pageable
    ) {
        return bookRepository.findAll(pageable);
    }
}
