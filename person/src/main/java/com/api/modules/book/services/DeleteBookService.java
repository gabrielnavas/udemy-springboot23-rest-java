package com.api.modules.book.services;

import com.api.modules.book.repositories.BookRepositoryJpa;
import com.api.modules.book.models.Book;
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
