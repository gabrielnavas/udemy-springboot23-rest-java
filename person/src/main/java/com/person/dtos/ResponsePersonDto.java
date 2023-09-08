package com.person.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

@JsonPropertyOrder({"id", "firstname", "lastname", "username", "birthday", "email", "createdAt", "updatedAt"})
public class ResponsePersonDto extends RepresentationModel<ResponsePersonDto> {
    @JsonProperty(value = "id")
    private String key;

    @JsonProperty(value = "first_name")
    private String firstname;
    @JsonProperty(value = "last_name")
    private String lastname;
    private Date birthday;
    private String username;
    private String email;
    @JsonProperty(value = "created_at")
    private LocalDateTime createdAt;
    @JsonProperty(value = "updated_at")
    private LocalDateTime updatedAt;

    public ResponsePersonDto(String key, String firstname, String lastname, Date birthday, String username, String email, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super();
        this.key = key;
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthday = birthday;
        this.username = username;
        this.email = email;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ResponsePersonDto that = (ResponsePersonDto) o;
        return Objects.equals(key, that.key) && Objects.equals(firstname, that.firstname) && Objects.equals(lastname, that.lastname) && Objects.equals(birthday, that.birthday) && Objects.equals(username, that.username) && Objects.equals(email, that.email) && Objects.equals(createdAt, that.createdAt) && Objects.equals(updatedAt, that.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), key, firstname, lastname, birthday, username, email, createdAt, updatedAt);
    }
}
