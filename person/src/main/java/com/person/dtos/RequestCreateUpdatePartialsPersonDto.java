package com.person.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

public record RequestCreateUpdatePartialsPersonDto(

        @Length(min = 1, max = 50)
        @NotNull
        @JsonProperty(value = "first_name")
        String firstname,

        @Length(min = 1, max = 25)
        @NotNull
        @JsonProperty(value = "last_name")
        String lastname,

        Date birthday,

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
        @JsonProperty(value = "password_confirmation")
        String passwordConfirmation
) {
}
