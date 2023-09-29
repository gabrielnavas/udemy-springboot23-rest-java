package com.api.modules.book.services;

import com.api.modules.book.repositories.BookRepositoryJpa;
import com.api.modules.book.models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

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
