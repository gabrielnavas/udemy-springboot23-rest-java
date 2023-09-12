package com.person.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.util.Date;
import java.util.Objects;

public class CreateUpdatePartialsPersonDto {

    @Length(min = 1, max = 50)
    @NotNull
    @JsonProperty(value = "first_name")
    String firstname;

    @Length(min = 1, max = 25)
    @NotNull
    @JsonProperty(value = "last_name")
    String lastname;

    Date birthday;

    @Length(min = 1, max = 25)
    @NotNull
    String username;

    @Email
    @NotNull
    String email;

    @Length(min = 8, max = 50)
    @NotNull
    String password;

    @Length(min = 8, max = 50)
    @NotNull
    @JsonProperty(value = "password_confirmation")
    String passwordConfirmation;

    public CreateUpdatePartialsPersonDto() {
        this("", "", new Date(), "", "", "", "");
    }

    public CreateUpdatePartialsPersonDto(String firstname, String lastname, Date birthday, String username, String email, String password, String passwordConfirmation) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthday = birthday;
        this.username = username;
        this.email = email;
        this.password = password;
        this.passwordConfirmation = passwordConfirmation;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirmation() {
        return passwordConfirmation;
    }

    public void setPasswordConfirmation(String passwordConfirmation) {
        this.passwordConfirmation = passwordConfirmation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateUpdatePartialsPersonDto that = (CreateUpdatePartialsPersonDto) o;
        return Objects.equals(firstname, that.firstname) && Objects.equals(lastname, that.lastname) && Objects.equals(birthday, that.birthday) && Objects.equals(username, that.username) && Objects.equals(email, that.email) && Objects.equals(password, that.password) && Objects.equals(passwordConfirmation, that.passwordConfirmation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstname, lastname, birthday, username, email, password, passwordConfirmation);
    }
}
