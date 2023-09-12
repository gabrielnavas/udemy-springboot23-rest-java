package com.person.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.sql.Date;

public class CreateBookDto {

    @NotNull
    @Length(min = 1, max = 255, message = "title must be between 2 and 255 characters")
    private String title;
    private String author;

    @JsonProperty(value = "launch_date")
    private Date launchDate;
    private Double price;

    public CreateBookDto(String title, String author, Date launchDate, Double price) {
        this.title = title;
        this.author = author;
        this.launchDate = launchDate;
        this.price = price;
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
