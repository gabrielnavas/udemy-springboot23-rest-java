package com.api.controllers.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.hateoas.RepresentationModel;

import java.util.Date;
import java.util.UUID;

@JsonPropertyOrder({"id", "title", "author", "price", "launchDate"})
public class ResponseBook extends RepresentationModel<ResponseBook> {

    @JsonProperty(value = "id")
    private UUID key;
    private String title;
    private String author;

    private Double price;

    @JsonProperty(value = "launch_date")
    private Date launchDate;

    public ResponseBook(UUID key, String title, String author, Double price, Date launchDate) {
        this.key = key;
        this.title = title;
        this.author = author;
        this.price = price;
        this.launchDate = launchDate;
    }

    public UUID getKey() {
        return key;
    }

    public void setKey(UUID key) {
        this.key = key;
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
