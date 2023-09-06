package com.person.models;

import java.util.Objects;
import java.util.UUID;

public class Person {
    private UUID id;
    private String firstname;
    private String lastname;
    private String username;
    private String password;
    private String email;

    public Person() {
        this(UUID.randomUUID(), "", "", "", "", "");
    }

    public Person(UUID id, String firstname, String lastname, String username, String email, String password) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public UUID getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return person.getId().compareTo(getId()) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstname, lastname, username, password, email);
    }
}
