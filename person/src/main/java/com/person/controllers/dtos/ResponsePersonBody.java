package com.person.controllers.dtos;

public record ResponsePersonBody(
        String id,
        String firstname,
        String lastname,
        String username,
        String email
) {
}
