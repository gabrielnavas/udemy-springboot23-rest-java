package com.person.controllers.dtos;

public record ResponseCreatePersonBody(
        String firstname,
        String lastname,
        String username,
        String email
) {
}
