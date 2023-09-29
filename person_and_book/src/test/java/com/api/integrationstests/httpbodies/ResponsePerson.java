package com.api.integrationstests.httpbodies;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class ResponsePerson implements Serializable {
    private String id;

    @JsonProperty(value = "first_name")
    private String firstname;
    @JsonProperty(value = "last_name")
    private String lastname;
    private Date birthday;
    private String username;
    private String email;
    @JsonProperty(value = "created_at")
    private Date createdAt;
    @JsonProperty(value = "updated_at")
    private Date updatedAt;

    public ResponsePerson() {
        this("", "", "", new Date(), "", "", new Date(), new Date());
    }

    public ResponsePerson(String id, String firstname, String lastname, Date birthday, String username, String email,
                          Date createdAt, Date updatedAt) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthday = birthday;
        this.username = username;
        this.email = email;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResponsePerson that = (ResponsePerson) o;
        return Objects.equals(id, that.id) && Objects.equals(firstname, that.firstname) && Objects.equals(lastname, that.lastname) && Objects.equals(birthday, that.birthday) && Objects.equals(username, that.username) && Objects.equals(email, that.email) && Objects.equals(createdAt, that.createdAt) && Objects.equals(updatedAt, that.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstname, lastname, birthday, username, email, createdAt, updatedAt);
    }
}

