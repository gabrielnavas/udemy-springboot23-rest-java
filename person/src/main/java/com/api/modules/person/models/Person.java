package com.api.modules.person.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "TB_PERSON")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "first_name", nullable = false, length = 50)
    private String firstname;

    @Column(name = "last_name", nullable = false, length = 20)
    private String lastname;
    @Column(nullable = false, unique = true, length = 20)
    private String username;

    @Column
    private Date birthday;

    @Column(nullable = false, length = 100)
    private String password;
    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;


    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updateAt;

    public Person() {
        this(UUID.randomUUID(), "", "", "", "", "", LocalDateTime.now(ZoneOffset.UTC), LocalDateTime.now(ZoneOffset.UTC));
    }

    public Person(UUID id, String firstname, String lastname, String username, String email, String password, LocalDateTime createdAt, LocalDateTime updateAt) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.password = password;
        this.email = email;
        this.createdAt = createdAt;
        this.updateAt = updateAt;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdateAt() {
        return updateAt;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdateAt(LocalDateTime updateAt) {
        this.updateAt = updateAt;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(id, person.id) && Objects.equals(firstname, person.firstname) && Objects.equals(lastname, person.lastname) && Objects.equals(username, person.username) && Objects.equals(password, person.password) && Objects.equals(email, person.email) && Objects.equals(createdAt, person.createdAt) && Objects.equals(updateAt, person.updateAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstname, lastname, username, password, email, createdAt, updateAt);
    }
}
