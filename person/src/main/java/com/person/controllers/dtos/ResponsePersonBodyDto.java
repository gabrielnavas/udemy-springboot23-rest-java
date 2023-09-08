package com.person.controllers.dtos;

import java.time.LocalDateTime;
import java.util.Date;

public record ResponsePersonBodyDto(
        String id,
        String firstname,
        String lastname,
        Date birthday,
        String username,
        String email,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
