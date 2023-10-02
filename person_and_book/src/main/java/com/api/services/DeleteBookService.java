package com.api.services;

import com.api.models.Book;
import com.api.repositories.BookRepositoryJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class DeleteBookService {

    @Autowired
    private BookRepositoryJpa bookRepository;

    public void execute(Book book) {
        bookRepository.delete(book);
    }
}
