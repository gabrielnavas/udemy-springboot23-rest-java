package com.person.controllers.dtos.v1;

import java.time.LocalDateTime;

public record ResponsePersonBodyDto(
        String id,
        String firstname,
        String lastname,
        String username,
        String email,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
