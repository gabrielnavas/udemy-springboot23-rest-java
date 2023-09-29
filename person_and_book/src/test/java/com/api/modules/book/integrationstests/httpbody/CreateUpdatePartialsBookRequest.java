package com.api.modules.book.integrationstests.httpbody;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.Objects;


public class CreateUpdatePartialsBookRequest {

    private String title;
    private String author;

    @JsonProperty(value = "launch_date")
    private Date launchDate;
    private Double price;

    public CreateUpdatePartialsBookRequest() {
        this("", "", new Date(), 0.0);
    }

    public CreateUpdatePartialsBookRequest(String title, String author, Date launchDate, Double price) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateUpdatePartialsBookRequest that = (CreateUpdatePartialsBookRequest) o;
        return Objects.equals(title, that.title) && Objects.equals(author, that.author) && Objects.equals(launchDate, that.launchDate) && Objects.equals(price, that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, author, launchDate, price);
    }
}
