package com.api.integrationstests.httpbody;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.UUID;

public class ResponseBook {

    private UUID id;
    private String title;
    private String author;

    private Double price;

    @JsonProperty(value = "launch_date")
    private Date launchDate;

    public ResponseBook() {
        this(UUID.randomUUID(), "", "", 0.0, new Date());
    }

    public ResponseBook(UUID id, String title, String author, Double price, Date launchDate) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.price = price;
        this.launchDate = launchDate;
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

    public Date getLaunchDate() {
        return launchDate;
    }

    public void setLaunchDate(Date launchDate) {
        this.launchDate = launchDate;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
