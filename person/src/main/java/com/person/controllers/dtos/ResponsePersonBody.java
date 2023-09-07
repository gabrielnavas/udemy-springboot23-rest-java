package com.person.controllers.dtos;

import java.time.LocalDateTime;

public record ResponsePersonBody(
        String id,
        String firstname,
        String lastname,
        String username,
        String email,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
