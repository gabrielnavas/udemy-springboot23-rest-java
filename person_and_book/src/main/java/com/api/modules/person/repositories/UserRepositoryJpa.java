package com.api.modules.person.repositories;

import com.api.modules.person.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface UserRepositoryJpa extends JpaRepository<User, UUID> {

    @Query(value = "SELECT u FROM User WHERE u.userName =: userName")
    User findByUsername(@Param("userName") String userName);
}
