package com.person.controllers.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public record CreatePersonDto(

        @Length(min = 1, max = 50)
        @NotNull
        String firstname,

        @Length(min = 1, max = 25)
        @NotNull
        String lastname,

        @Length(min = 1, max = 25)
        @NotNull
        String username,

        @Email
        @NotNull
        String email,

        @Length(min = 8, max = 50)
        @NotNull
        String password,

        @Length(min = 8, max = 50)
        @NotNull
        String passwordConfirmation
) {
}
