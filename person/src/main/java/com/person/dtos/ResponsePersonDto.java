package com.person.dtos;

import java.time.LocalDateTime;
import java.util.Date;

public record ResponsePersonDto(
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
