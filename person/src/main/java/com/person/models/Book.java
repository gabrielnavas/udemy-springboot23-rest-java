package com.person.models;

import jakarta.persistence.*;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "tb_book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true, nullable = false)
    private String title;

    @Column(nullable = false)
    private String author;

    private Double price;

    @Column(name = "launch_date")
    Date launchData;

    public Book(UUID id, String title, String author, Double price, Date launchData) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.price = price;
        this.launchData = launchData;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Date getLaunchData() {
        return launchData;
    }

    public void setLaunchData(Date launchData) {
        this.launchData = launchData;
    }
}
