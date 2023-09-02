package com.person.controllers.dtos;

public record ResponseCreatePersonBody(
        String id,
        String firstname,
        String lastname,
        String username,
        String email
) {
}
