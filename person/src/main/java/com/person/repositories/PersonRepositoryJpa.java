package com.person.repositories;

import com.person.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PersonRepositoryJpa extends JpaRepository<Person, UUID> {
    Optional<Person> findByEmail(String email);

    Optional<Person> findByUsername(String email);
}
