package com.api.modules.user.repositories;

import com.api.modules.user.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface UserRepositoryJpa extends JpaRepository<User, UUID> {

    @Query(value = "SELECT u FROM User u WHERE u.username =: username")
    Optional<User> findByUsername(@Param("username") String username);
}
