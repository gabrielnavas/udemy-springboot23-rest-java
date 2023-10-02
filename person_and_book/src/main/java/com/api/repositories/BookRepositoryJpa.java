package com.api.repositories;

import com.api.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface BookRepositoryJpa extends JpaRepository<Book, UUID> {
    Optional<Book> findByTitle(String title);
}
