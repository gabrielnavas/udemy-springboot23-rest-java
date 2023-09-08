package com.person.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.time.LocalDateTime;
import java.util.Date;

@JsonPropertyOrder({"id", "firstname", "lastname", "username", "birthday", "email", "createdAt", "updatedAt"})
public record ResponsePersonDto(
        String id,

        @JsonProperty(value = "first_name")
        String firstname,
        @JsonProperty(value = "last_name")
        String lastname,
        Date birthday,
        String username,
        String email,
        @JsonProperty(value = "created_at")
        LocalDateTime createdAt,
        @JsonProperty(value = "updated_at")
        LocalDateTime updatedAt
) {
}
