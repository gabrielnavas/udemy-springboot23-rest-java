package com.person.repositories;

import com.person.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BookRepositoryJpa extends JpaRepository<Book, UUID> {
}
