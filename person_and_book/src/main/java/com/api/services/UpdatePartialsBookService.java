package com.api.services;

import com.api.dtos.CreateBookDto;
import com.api.exceptions.ObjectAlreadyExistsWithException;
import com.api.exceptions.ObjectNotFoundException;
import com.api.models.Book;
import com.api.repositories.BookRepositoryJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UpdatePartialsBookService {

    @Autowired
    private BookRepositoryJpa bookRepository;

    public void execute(UUID id, CreateBookDto dto) {
        Optional<Book> bookFound = bookRepository.findById(id);
        if (bookFound.isEmpty()) {
            throw new ObjectNotFoundException("book not found");
        }

        Optional<Book> bookByTitleFound = bookRepository.findById(id);
        if (bookByTitleFound.isPresent() && !bookByTitleFound.get().getId().equals(id)) {
            throw new ObjectAlreadyExistsWithException("book", "title");
        }

        Book book = bookFound.get();
        book.setTitle(dto.getTitle());
        book.setAuthor(dto.getAuthor());
        book.setPrice(dto.getPrice());
        book.setLaunchData(dto.getLaunchDate());

        bookRepository.save(book);
    }
}
