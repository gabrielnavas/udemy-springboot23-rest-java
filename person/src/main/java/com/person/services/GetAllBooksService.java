package com.person.services;

import com.person.models.Book;
import com.person.repositories.BookRepositoryJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class GetAllBooksService {

    @Autowired
    private BookRepositoryJpa bookRepository;

    public List<Book> execute(
            @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
            Pageable pageable
    ) {
        return bookRepository.findAll();
    }
}
