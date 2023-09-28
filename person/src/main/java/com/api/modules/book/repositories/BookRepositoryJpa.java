package com.api.modules.book.repositories;

import com.api.modules.book.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface BookRepositoryJpa extends JpaRepository<Book, UUID> {
    Optional<Book> findByTitle(String title);
}
